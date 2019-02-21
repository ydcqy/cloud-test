package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.client.AbstractNettyClient;
import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.handler.Handler;
import com.ydcqy.ynet.util.SerializationType;

import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public class YrpcClient extends AbstractNettyClient {

    private static final Handler handler = new YrpcClientHandler();

    public YrpcClient(String remoteHost, int remotePort) {
        super(remoteHost, remotePort);
    }

    public YrpcClient(InetSocketAddress remoteAddress) {
        super(remoteAddress);
    }

    @Override
    public Codec getCodec() {
        return new YrpcClientCodec(SerializationType.JSON);
    }

    @Override
    public Handler getHandler() {
        return handler;
    }


}
