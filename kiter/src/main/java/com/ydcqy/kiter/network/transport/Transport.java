package com.ydcqy.kiter.network.transport;

import com.ydcqy.kiter.network.Client;
import com.ydcqy.kiter.network.RemoteException;
import com.ydcqy.kiter.network.Server;

/**
 * @author xiaoyu
 */
public interface Transport {
    Server bind(int port) throws RemoteException;

    Client connect(String ip, int port) throws RemoteException;
}
