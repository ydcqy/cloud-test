package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.client.Client;
import com.ydcqy.ynet.exception.RemoteException;
import com.ydcqy.ynet.handler.AbstractNettyClientHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaoyu
 */
class YrpcClientHandler extends AbstractNettyClientHandler {
    private static final Logger logger = LoggerFactory.getLogger(YrpcClientHandler.class);

    public YrpcClientHandler() {
    }

    @Override
    public void open(Channel channel) throws RemoteException {
        logger.info("{} is opened,ch: {}", channel, System.identityHashCode(channel));
    }

    @Override
    public void close(Channel channel) throws RemoteException {
        logger.warn("{} is closed", channel);
    }

    @Override
    public void sent(Channel channel, Object message) throws RemoteException {
        if (logger.isDebugEnabled()) {
            logger.debug("{} sent message: {}", channel, message);
        }
    }

    @Override
    public void receive(Channel channel, Object message) throws RemoteException {
        if (logger.isDebugEnabled()) {
            logger.debug("{} receive message: {}", channel, message);
        }
    }

    @Override
    public void caught(Channel channel, Throwable cause) throws RemoteException {
        logger.error("" + channel, cause);
    }
}
