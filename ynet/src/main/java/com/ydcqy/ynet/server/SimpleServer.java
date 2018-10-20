package com.ydcqy.ynet.server;

import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.codec.SimpleServerCodec;
import com.ydcqy.ynet.handler.Handler;
import com.ydcqy.ynet.handler.SimpleServerHandler;

/**
 * @author xiaoyu
 */
public class SimpleServer extends AbstractNettyServer {
    private static final Handler handler = new SimpleServerHandler();

    public SimpleServer(int port) {
        super(port);
    }

    @Override
    public Codec getCodec() {
        return new SimpleServerCodec();
    }

    @Override
    public Handler getHandler() {
        return handler;
    }
}
