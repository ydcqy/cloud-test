package com.ydcqy.ynet.ws;

import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.handler.Handler;
import com.ydcqy.ynet.server.AbstractNettyServer;

import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public class WebSocketServer extends AbstractNettyServer {
    private static final WebSocketServerCodec WEB_SOCKET_SERVER_CODEC = new WebSocketServerCodec();
    private static final WebSocketServerHandler WEB_SOCKET_SERVER_HANDLER = new WebSocketServerHandler();

    public WebSocketServer(int port, WebSocketRequestHandler handler) {
        handler.setWebSocketServer(this);
        WEB_SOCKET_SERVER_HANDLER.setHandler(handler);
        bind(new InetSocketAddress(port));
    }

    @Override
    public Codec getCodec() {
        return WEB_SOCKET_SERVER_CODEC;
    }

    @Override
    public Handler getHandler() {
        return WEB_SOCKET_SERVER_HANDLER;
    }


}
