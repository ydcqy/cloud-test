package com.ydcqy.ynet.server;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * @author xiaoyu
 */
abstract class AbstractServer implements Server {
    private volatile boolean isClose;
    private InetSocketAddress bindAddress;

    public AbstractServer(int port) {
        this(new InetSocketAddress(port));
    }

    public AbstractServer(String host, int port) {
        this(new InetSocketAddress(host, port));
    }

    public AbstractServer(InetSocketAddress bindAddress) {
        this.bindAddress = bindAddress;
    }

    @Override
    public void close() {
        if (isClose) {
            throw new IllegalStateException("The server has already been closed");
        }
        isClose = true;
    }

    @Override
    public InetSocketAddress getLocalAddress() {
        Objects.requireNonNull(bindAddress);
        return bindAddress;
    }

}
