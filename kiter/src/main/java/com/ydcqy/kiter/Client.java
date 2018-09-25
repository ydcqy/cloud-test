package com.ydcqy.kiter;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.security.KeyStore;
import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
@Slf4j
public class Client {
    private static SSLEngine sslEngine;
    private static SSLContext sslContext;

    private static final String SSL_TYPE = "TLSv1.2";
    private static final String KS_TYPE = "PKCS12";
    private static final char[] PASSWORD = "123456".toCharArray();
    private static final String X509 = "SunX509";


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1
                ; i++) {

            try {
                io();
                System.out.println(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LockSupport.park();
    }

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
        sslContext.init(null, null, null);

        sslEngine = sslContext.createSSLEngine();
        sslEngine.setUseClientMode(true);
        log.info("加载SSL完成!");
        System.out.println(sslEngine.getSession().getApplicationBufferSize());
        System.out.println(sslEngine.getSession().getPacketBufferSize());
    }

    private static void nio() throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 8 * 1024);
        socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 8 * 1024);
//        initSSL();
//        sslEngine.beginHandshake();
//        socketChannel.setOption(StandardSocketOptions., 10240);
        socketChannel.connect(new InetSocketAddress("xiaoyu-pc", 1111));
        Socket socket = socketChannel.socket();
//        socket.setSendBufferSize(512000);
        SocketChannel channel = socket.getChannel();
        log.info("客户端io-->服务端 {} {}", socket, channel);
//        OutputStream out = socket.getOutputStream();
        for (; ; ) {
//            Thread.sleep(1000);
            String s = new Scanner(System.in).nextLine();

//            log.info("========== " + sslEngine.getHandshakeStatus());
            log.info(String.valueOf(socket.isBound()));
            log.info(String.valueOf(channel.isConnected()));
            log.info(String.valueOf(socket.isClosed()));
            log.info(String.valueOf(socket.isInputShutdown()));
            log.info(String.valueOf(socket.isOutputShutdown()));

//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
//            out.append("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
//            writer.flush();
//            out.write(s.getBytes());
//            socket.sendUrgentData(0xff);
//            ByteBuffer netData = ByteBuffer.allocate(sslEngine.getSession().getPacketBufferSize());
//            SSLEngineResult result = sslEngine.wrap(ByteBuffer.wrap(s.getBytes()), netData);
//            log.info("打包结果,HandshakeStatus:{},Status:{},pktBuf:{}",
//                    result.getHandshakeStatus(), result.getStatus(),
//                    netData);
//            log.info("HandshakeStatus:{}", sslEngine.getHandshakeStatus());
//
//
//            netData.flip();
            channel.write(ByteBuffer.wrap(s.getBytes()));
//            for (; ; ) {
//                log.info("HandshakeStatus:{}", sslEngine.getHandshakeStatus());
//                ByteBuffer rcvBuf = ByteBuffer.allocate(4 * 1024);
//                int read = channel.read(rcvBuf);
//                System.out.println(read);
//                Thread.sleep(1000);
//                ByteBuffer packetByteBuffer = ByteBuffer.allocate(sslEngine.getSession().getPacketBufferSize());
//                log.info("HandshakeStatus:{}", sslEngine.getHandshakeStatus());
//                rcvBuf.flip();
//                result = sslEngine.unwrap(rcvBuf, packetByteBuffer);
//                log.info("解包结果,HandshakeStatus:{},Status:{},pktBuf:{},content:{}",
//                        result.getHandshakeStatus(), result.getStatus(),
//                        packetByteBuffer, new String(packetByteBuffer.array()));
//                sslEngine.getDelegatedTask().run();
//                log.info("HandshakeStatus:{}", sslEngine.getHandshakeStatus());
//
//                netData = ByteBuffer.allocate(sslEngine.getSession().getPacketBufferSize());
//                result = sslEngine.wrap(ByteBuffer.wrap(s.getBytes()), netData);
//                log.info("打包结果,HandshakeStatus:{},Status:{},pktBuf:{}",
//                        result.getHandshakeStatus(), result.getStatus(),
//                        netData);
//                log.info("HandshakeStatus:{}", sslEngine.getHandshakeStatus());
//                netData.flip();
//                channel.write(netData);
//            }
        }
    }

    private static void io() throws Exception {
        Socket socket = new Socket("localhost", 1111);
        log.info("客户端io-->服务端 {}", socket);
        for (; ; ) {
            Thread.sleep(1000);
            log.info("==========");
            log.info(String.valueOf(socket.isBound()));
            log.info(String.valueOf(socket.isConnected()));
            log.info(String.valueOf(socket.isClosed()));
            log.info(String.valueOf(socket.isInputShutdown()));
            log.info(String.valueOf(socket.isOutputShutdown()));

            OutputStream out = socket.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.append("哈哈哈");
            writer.newLine();
            writer.flush();
        }
    }

}
