package com.ydcqy.kiter.network.transport;

import com.ydcqy.kiter.network.Client;
import com.ydcqy.kiter.network.RemoteException;
import com.ydcqy.kiter.network.Server;
import com.ydcqy.kiter.network.netty.NettyServer;

import java.net.InetSocketAddress;
import java.net.URL;

/**
 * @author xiaoyu
 */
public class NettyTransport implements Transport {
    @Override
    public Server bind(int port) throws RemoteException {
        return new NettyServer(new InetSocketAddress(port));
    }

    @Override
    public Client connect(String ip, int port) throws RemoteException {
        return null;
    }

    public static void main(String[] args) throws RemoteException {
        NettyTransport nettyServerTransport = new NettyTransport();
        Server bind = nettyServerTransport.bind(8111);
        System.out.println(bind.isBound());
    }
}
