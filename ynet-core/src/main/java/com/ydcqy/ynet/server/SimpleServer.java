package com.ydcqy.ynet.server;

import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public class SimpleServer extends AbstractNettyServer {
    public SimpleServer(int port) {
        super(port);
    }

    public SimpleServer(String host, int port) {
        super(host, port);
    }

    public SimpleServer(InetSocketAddress bindAddress) {
        super(bindAddress);
    }

}
