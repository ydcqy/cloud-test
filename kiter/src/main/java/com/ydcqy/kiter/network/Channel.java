package com.ydcqy.kiter.network;

import java.io.Closeable;
import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public interface Channel extends Closeable {
    InetSocketAddress getLocalAddress();

    InetSocketAddress getRemoteAddress();

    Boolean isConnected();

    void send(Object msg);

    void receive(Object msg);

}
