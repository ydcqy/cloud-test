package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.handler.Handler;
import com.ydcqy.ynet.server.AbstractNettyServer;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class YrpcServer extends AbstractNettyServer {
    private static final Handler handler = new YrpcServerHandler();

    public YrpcServer(int port) {
        super(port);
    }

    @Override
    public Codec getCodec() {
        return new YrpcServerCodec();
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    public static void main(String[] args) {
        YrpcServer yrpcServer = new YrpcServer(1111);
        System.out.println("启动结果：" + yrpcServer.isOpen());
        LockSupport.park();
    }
}
