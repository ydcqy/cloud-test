package com.ydcqy.kiter.network;

import java.net.InetSocketAddress;
import java.util.Collection;

/**
 * @author xiaoyu
 */
public interface Server {
    boolean isBound();

    Collection<Channel> getChannels();

    Channel getChannel(InetSocketAddress remoteAddress);
}
