package com.ydcqy.ynet.channel;

import java.io.Closeable;
import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public interface Channel extends Closeable {
    /**
     * isOpen
     *
     * @return
     */
    boolean isOpen();

    void send(Object message);

    /**
     * getLocalAddress
     *
     * @return
     */
    InetSocketAddress getLocalAddress();

    /**
     * getRemoteAddress
     *
     * @return
     */
    InetSocketAddress getRemoteAddress();
}
