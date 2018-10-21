package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.client.AbstractNettyClient;
import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.handler.Handler;

import java.net.InetSocketAddress;
import java.util.concurrent.locks.LockSupport;

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
        return new YrpcClientCodec();
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    public static void main(String[] args) throws Exception {

        YrpcClient client = new YrpcClient("127.0.0.1", 1111);
        YrpcRequest request = new YrpcRequest();
        YrpcResponse response = client.send(request);
        System.out.println(response);

        LockSupport.park();
    }
}
