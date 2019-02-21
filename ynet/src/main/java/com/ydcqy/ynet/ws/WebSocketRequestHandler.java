package com.ydcqy.ynet.ws;

/**
 * @author xiaoyu
 */
public abstract class WebSocketRequestHandler {
    private WebSocketServer webSocketServer;

    public abstract void onOpen(WebSocketInfo info);

    public abstract void onMessag(WebSocketInfo info, String message);

    public abstract void onClose(WebSocketInfo info);

    void setWebSocketServer(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    protected WebSocketServer getWebSocketServer() {
        return webSocketServer;
    }

}
