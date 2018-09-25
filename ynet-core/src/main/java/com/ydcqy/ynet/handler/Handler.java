package com.ydcqy.ynet.handler;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.exception.RemoteException;

/**
 * Handler
 *
 * @author xiaoyu
 */
public interface Handler {
    /**
     * @param channel
     * @throws RemoteException
     */
    void open(Channel channel) throws RemoteException;

    /**
     * @param channel
     * @throws RemoteException
     */
    void close(Channel channel) throws RemoteException;

    /**
     * send
     *
     * @param channel
     * @param message
     * @throws RemoteException
     */
    void send(Channel channel, Object message) throws RemoteException;

    /**
     * receive
     *
     * @param channel
     * @param message
     * @throws RemoteException
     */
    void receive(Channel channel, Object message) throws RemoteException;

    /**
     * caught
     *
     * @param channel
     * @param throwable
     * @throws RemoteException
     */
    void caught(Channel channel, Throwable throwable) throws RemoteException;

}