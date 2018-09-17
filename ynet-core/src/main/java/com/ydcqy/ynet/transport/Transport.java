package com.ydcqy.ynet.transport;

import java.io.Closeable;
import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public interface Transport extends Closeable, Cloneable {
    boolean isOpened();

    boolean isTransportable();

    InetSocketAddress getLocalAddress();

}
