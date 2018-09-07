package com.ydcqy.kiter;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
@Slf4j
public class Server {
    private static SSLEngine  sslEngine;
    private static SSLContext sslContext;
    private static final String SSL_TYPE = "SSL";
    private static final String KS_TYPE  = "JKS";
    private static final String X509     = "SunX509";

    private static void initSSL() throws Exception {
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(X509);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(X509);

        InputStream jksFile = ClassLoader.getSystemResourceAsStream("ssl/app.key");

        char[] svrPassword = null;
        KeyStore serverKeyStore = KeyStore.getInstance(KS_TYPE);
        serverKeyStore.load(jksFile, svrPassword);
        System.out.println(serverKeyStore);


        kmf.init(serverKeyStore, svrPassword);

        String clientKeyStoreFile = "c:\\client.jks";
        String cntPassphrase = "client";
        char[] cntPassword = cntPassphrase.toCharArray();
        KeyStore clientKeyStore = KeyStore.getInstance(KS_TYPE);
        clientKeyStore.load(new FileInputStream(clientKeyStoreFile), cntPassword);
        tmf.init(clientKeyStore);
        sslContext = SSLContext.getInstance(SSL_TYPE);
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        sslEngine = sslContext.createSSLEngine();
        sslEngine.setUseClientMode(false);

    }

    public static void main(String[] args) throws Exception {
        nio();
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
                    log.info("收到数据,size:{},buf:{},content:{}", len, rcvBuf.toString(), new String(rcvBuf.array()));
                }
            }
        }
    }

    private static void io() throws Exception {
        ServerSocket serverSocket = new ServerSocket(1111, 20);
//        Socket socket;
//        if ((socket = serverSocket.accept()) != null) {
//            log.info("服务端<--客户端 {}", socket);
//            try {
//                InputStream input = socket.getInputStream();
//                for (; ; ) {
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//                    log.info(reader.readLine());
//                }
//            } catch (IOException e) {
//                log.error(e.getMessage(), e);
//            }
//        }
        LockSupport.park();
    }
}
