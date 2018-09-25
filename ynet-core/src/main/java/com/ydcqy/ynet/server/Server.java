package com.ydcqy.ynet.server;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.transport.Transport;

import java.util.Set;

/**
 * @author xiaoyu
 */
public interface Server extends Transport {
    int clientCount();

    Set<Channel> getClientChannels();
}
