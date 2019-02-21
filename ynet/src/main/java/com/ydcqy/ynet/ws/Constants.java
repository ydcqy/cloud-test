package com.ydcqy.ynet.ws;

import io.netty.util.AttributeKey;

/**
 * @author xiaoyu
 */
public interface Constants {
    AttributeKey<WebSocketInfo> WEB_SOCKET_INFO_ATTRIBUTE_KEY = AttributeKey.newInstance("WSI");
}
