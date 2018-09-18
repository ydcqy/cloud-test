package com.ydcqy.kiter;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
@Slf4j
public class Server {
    private static SSLEngine  sslEngine;
    private static SSLContext sslContext;

    private static final String SSL_TYPE = "TLSv1.2";
    private static final String KS_TYPE  = "PKCS12";
    private static final char[] PASSWORD = "123456".toCharArray();
    private static final String X509     = "SunX509";


    private static void initSSL() throws Exception {
        log.info("加载SSL...");
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(X509);
        InputStream jksFile = ClassLoader.getSystemResourceAsStream("ssl/app.pfx");
        KeyStore serverKeyStore = KeyStore.getInstance(KS_TYPE);
        serverKeyStore.load(jksFile, PASSWORD);
        kmf.init(serverKeyStore, PASSWORD);

//        TrustManagerFactory tmf = TrustManagerFactory.getInstance(X509);
//        KeyStore clientKeyStore = KeyStore.getInstance(KS_TYPE);
//        clientKeyStore.load(jksFile, PASSWORD);
//        tmf.init(clientKeyStore);

        sslContext = SSLContext.getInstance(SSL_TYPE);
        sslContext.init(kmf.getKeyManagers(), null, null);

        sslEngine = sslContext.createSSLEngine();
        sslEngine.setUseClientMode(false);
        sslEngine.setNeedClientAuth(false);//单向验证
        log.info("加载SSL完成!");
        System.out.println(sslEngine.getSession().getApplicationBufferSize());
        System.out.println(sslEngine.getSession().getPacketBufferSize());
    }

    public static void main(String[] args) throws Exception {

        io();
    }

    private static void nio() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 8 * 1024);
        serverSocketChannel.bind(new InetSocketAddress("localhost", 1111), 1024);
        serverSocketChannel.configureBlocking(false);
        initSSL();

        Selector selector = Selector.open();
        SelectionKey serverkey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        log.info("keys size:{},selectedKeys size:{}", selector.keys().size(), selector.selectedKeys().size());
        log.info("isAcceptable:{},isConnectable:{},isReadable:{},isWritable:{},isValid:{}", serverkey.isAcceptable(), serverkey.isConnectable(), serverkey.isReadable(), serverkey.isWritable(), serverkey.isValid());

        log.info("监听端口...");
        for (; ; ) {
            int readyChannels = selector.select();
            log.info("事件,keys size:{},selectedKeys size:{},readyChannels:{}", selector.keys().size(), selector.selectedKeys().size(), readyChannels);
            if (readyChannels == 0) {
                continue;
            }
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                log.info("isAcceptable:{},isConnectable:{},isReadable:{},isWritable:{},isValid:{}", key.isAcceptable(), key.isConnectable(), key.isReadable(), key.isWritable(), key.isValid());
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = ssc.accept();
                    log.info("建立新连接,{}", socketChannel);
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer rcvBuf = ByteBuffer.allocate(4 * 1024);
                    int len = 0;
                    try {
                        len = socketChannel.read(rcvBuf);
                        if (len == -1) {
                            socketChannel.close();
                        }
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                        socketChannel.close();
                    }
                    ByteBuffer packetByteBuffer = ByteBuffer.allocate(sslEngine.getSession().getPacketBufferSize());
                    ;
                    SSLEngineResult result = null;
                    try {
                        rcvBuf.flip();
                        for (; ; ) {
                            new Scanner(System.in).nextLine();
                            log.info("HandshakeStatus:{}", sslEngine.getHandshakeStatus());
                            switch (sslEngine.getHandshakeStatus()) {
                                case FINISHED:
                                    break;
                                case NEED_UNWRAP:
                                    log.info("握手解包...");
                                    result = sslEngine.unwrap(rcvBuf, packetByteBuffer);
                                    log.info("解包结果,HandshakeStatus:{},Status:{},pktBuf:{},content:{}",
                                            result.getHandshakeStatus(), result.getStatus(),
                                            packetByteBuffer, new String(packetByteBuffer.array()));
                                    break;
                                case NEED_TASK:
                                    sslEngine.getDelegatedTask().run();
                                    break;
                                case NEED_WRAP:
                                    log.info("握手打包...");
                                    packetByteBuffer = ByteBuffer.allocate(sslEngine.getSession().getPacketBufferSize());
                                    result = sslEngine.wrap(ByteBuffer.wrap("Hello\n".getBytes()), packetByteBuffer);
                                    log.info("打包结果,HandshakeStatus:{},Status:{},pktBuf:{}",
                                            result.getHandshakeStatus(), result.getStatus(),
                                            packetByteBuffer);

                                    packetByteBuffer.flip();
                                    socketChannel.write(packetByteBuffer);
                                    break;
                                case NOT_HANDSHAKING:
                                    sslEngine.beginHandshake();
                                    log.info("开始握手...");
                                    break;
                            }
                            log.info("收到数据,size:{},buf:{},content:{}", len, rcvBuf, new String(rcvBuf.array()));
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        socketChannel.close();
                    }
                }
            }
        }
    }

    private static void io() throws Exception {
        ServerSocket serverSocket = new ServerSocket(1111, 20);
        Socket socket;
        if ((socket = serverSocket.accept()) != null) {
            log.info("服务端<--客户端 {}", socket);
            try {
                InputStream input = socket.getInputStream();
                for (; ; ) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    log.info(reader.readLine());
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        LockSupport.park();
    }
}
