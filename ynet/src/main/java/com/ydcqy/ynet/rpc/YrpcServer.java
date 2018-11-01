package com.ydcqy.ynet.rpc;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.handler.Handler;
import com.ydcqy.ynet.rpc.config.ServerConfig;
import com.ydcqy.ynet.server.AbstractNettyServer;
import com.ydcqy.ynet.util.SerializationType;
import io.netty.util.ResourceLeakDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @author xiaoyu
 */
public class YrpcServer extends AbstractNettyServer {

    private static final Logger LOG = LoggerFactory.getLogger(YrpcClient.class);

    static {
        try {
            Field rootLoggerField = LoggerContext.class.getDeclaredField("root");
            rootLoggerField.setAccessible(true);
            ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) rootLoggerField.get(LoggerFactory.getILoggerFactory());
            rootLogger.setLevel(Level.INFO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final YrpcServerHandler handler = new YrpcServerHandler();

    public YrpcServer(int port, ServerConfig config) {
        handler.setServerConfig(config);
        bind(new InetSocketAddress(port));
    }

    @Override
    public Codec getCodec() {
        return new YrpcServerCodec(SerializationType.JSON);
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    public static void main(String[] args) throws IOException {
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);

        ServerConfig config = new ServerConfig();
        config.addService(HelloService.class.getName(), new HelloServiceImpl());
        YrpcServer yrpcServer = new YrpcServer(1111, config);
        System.out.println("启动结果：" + yrpcServer.isOpen());
        while (new Scanner(System.in).nextLine() != null) {
            System.out.println(yrpcServer.getClientChannelMap().size());
        }
    }

}
