package com.ydcqy.kiter;

import jdk.management.resource.internal.inst.ServerSocketChannelImplRMHooks;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.StandardSocketOptions;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws Exception {
        nio();
    }

    private static void nio() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 5);
        serverSocketChannel.bind(new InetSocketAddress("localhost", 1111), 1);
        Socket socket;
        SocketChannel channel;
        while ((socket = serverSocketChannel.accept().socket()) != null) {
            channel = socket.getChannel();
            log.info("服务端<--客户端 {} {}", socket, channel);
            try {
                InputStream input = socket.getInputStream();
                byte[] buffer = new byte[2048];
                for (; ; ) {
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//                    String s = reader.readLine();
                    int len = input.read(buffer);
                    log.info("读取到数据，len:{}", len);
                    if (len == -1) {
                        log.info("==========");
                        log.info(String.valueOf(socket.isBound()));
                        log.info(String.valueOf(channel.isConnected()));
                        log.info(String.valueOf(socket.isClosed()));
                        log.info(String.valueOf(socket.isInputShutdown()));
                        log.info(String.valueOf(socket.isOutputShutdown()));
//                       socket.close();
                        break;
                    }
//                    if (s == null) {
//                        break;
//                    }
//                    Selector selector = Selector.open();
//                    channel.register(selector, SelectionKey.OP_READ);

                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        LockSupport.park();
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
