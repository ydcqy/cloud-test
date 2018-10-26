package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.exception.RemoteException;
import com.ydcqy.ynet.handler.AbstractNettyServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

/**
 * @author xiaoyu
 */
class YrpcServerHandler extends AbstractNettyServerHandler {
    private static final Logger logger = LoggerFactory.getLogger(YrpcServerHandler.class);

    public YrpcServerHandler() {
    }

    @Override
    public void open(Channel channel) throws RemoteException {
        logger.info("{} is opened", channel);
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
        Map<String, Channel> channelMap = getChannelMap();
        Collection<Channel> channels = channelMap.values();
        for (Channel ch : channels) {
            if (ch != channel) {
                ch.send(message);
            }
        }
    }

    @Override
    public void caught(Channel channel, Throwable cause) throws RemoteException {
        logger.error("" + channel, cause);
        if (!(cause instanceof RemoteException)) {
            try {
                channel.close();
            } catch (Exception e) {
            }
        }
    }
}
