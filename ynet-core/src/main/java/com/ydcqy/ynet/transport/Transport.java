package com.ydcqy.ynet.transport;

import java.io.Closeable;
import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public interface Transport extends Closeable {
    boolean isOpen();

    boolean isSSL();

    boolean isTransportable();

    InetSocketAddress getLocalAddress();

}
