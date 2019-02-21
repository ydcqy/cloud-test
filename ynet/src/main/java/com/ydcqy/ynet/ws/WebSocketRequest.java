package com.ydcqy.ynet.ws;

/**
 * @author xiaoyu
 */
class WebSocketRequest {
    private WebSocketInfo webSocketInfo;
    private String message;

    public WebSocketInfo getWebSocketInfo() {
        return webSocketInfo;
    }

    public void setWebSocketInfo(WebSocketInfo webSocketInfo) {
        this.webSocketInfo = webSocketInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
