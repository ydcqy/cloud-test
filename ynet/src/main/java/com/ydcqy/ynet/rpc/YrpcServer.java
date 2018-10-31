package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.handler.Handler;
import com.ydcqy.ynet.rpc.config.ServerConfig;
import com.ydcqy.ynet.server.AbstractNettyServer;
import com.ydcqy.ynet.util.SerializationType;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @author xiaoyu
 */
public class YrpcServer extends AbstractNettyServer {
    private static final YrpcServerHandler handler = new YrpcServerHandler();

    public YrpcServer(int port, ServerConfig config) {
        handler.setServerConfig(config);
        bind(new InetSocketAddress(port));
    }

    @Override
    public Codec getCodec() {
        return new YrpcServerCodec(SerializationType.PROTO);
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    public static void main(String[] args) throws IOException {
        ServerConfig config = new ServerConfig();
        config.addService(HelloService.class.getName(), new HelloServiceImpl());
        YrpcServer yrpcServer = new YrpcServer(1111, config);
        System.out.println("启动结果：" + yrpcServer.isOpen());
        while (new Scanner(System.in).nextLine() != null) {
            System.out.println(yrpcServer.getClientChannelMap().size());
        }
    }

}
