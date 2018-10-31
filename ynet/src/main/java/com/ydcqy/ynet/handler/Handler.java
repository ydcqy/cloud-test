package com.ydcqy.ynet.handler;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.exception.RemoteException;

import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Handler
 *
 * @author xiaoyu
 */
public interface Handler {
    /**
     * getExecutorService
     *
     * @return
     */
    ExecutorService getExecutorService();

    /**
     * getChannelMap
     *
     * @return
     */
    Map<String, Channel> getChannelMap();

    /**
     * open
     *
     * @param channel
     * @throws RemoteException
     */
    void open(Channel channel) throws RemoteException;

    /**
     * close
     *
     * @param channel
     * @throws RemoteException
     */
    void close(Channel channel) throws RemoteException;

    /**
     * sent
     *
     * @param channel
     * @param message
     * @throws RemoteException
     */
    void sent(Channel channel, Object message) throws RemoteException;

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
     * @param cause
     * @throws RemoteException
     */
    void caught(Channel channel, Throwable cause) throws RemoteException;

}