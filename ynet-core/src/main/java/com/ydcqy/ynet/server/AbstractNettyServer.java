package com.ydcqy.ynet.server;

import io.netty.bootstrap.ServerBootstrap;

import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public abstract class AbstractNettyServer extends AbstractServer {
    private ServerBootstrap bootstrap = new ServerBootstrap();

    public AbstractNettyServer(int port) {
        super(port);
    }

    public AbstractNettyServer(String host, int port) {
        super(host, port);
    }

    public AbstractNettyServer(InetSocketAddress bindAddress) {
        super(bindAddress);
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public int clientCount() {
        return 0;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isSSL() {
        return false;
    }

    @Override
    public boolean isTransportable() {
        return false;
    }
}
