package com.ydcqy.kiter.network;

import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public abstract class AbstractServer implements Server {
    private InetSocketAddress bindAddress;

    public AbstractServer(InetSocketAddress bindAddress) throws RemoteException {
        this.bindAddress = bindAddress;
        try {
            doOpen();
        } catch (Throwable throwable) {
            throw new RemoteException("Failed to bind", throwable);
        }
    }


    protected abstract void doOpen() throws Throwable;

    protected InetSocketAddress getBindAddress() {
        return bindAddress;
    }
}
