package com.ydcqy.ynet.server;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.transport.Transport;

import java.util.Map;

/**
 * @author xiaoyu
 */
public interface Server extends Transport {

    /**
     * getClientChannelMap
     *
     * @return ip:port,channel
     */
    Map<String, Channel> getClientChannelMap();

}
