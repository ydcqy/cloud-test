package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.handler.Handler;
import com.ydcqy.ynet.rpc.config.ServerConfig;
import com.ydcqy.ynet.server.AbstractNettyServer;
import com.ydcqy.ynet.util.SerializationType;

import java.net.InetSocketAddress;

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
        return new YrpcServerCodec(SerializationType.JSON);
    }

    @Override
    public Handler getHandler() {
        return handler;
    }


}
