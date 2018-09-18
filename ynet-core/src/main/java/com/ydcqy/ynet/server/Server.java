package com.ydcqy.ynet.server;

import com.ydcqy.ynet.transport.Transport;

/**
 * @author xiaoyu
 */
public interface Server extends Transport {
    int clientCount();
}
