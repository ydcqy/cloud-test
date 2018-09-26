package com.ydcqy.ynet.handler;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.exception.RemoteException;
import com.ydcqy.ynet.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

/**
 * @author xiaoyu
 */
public class SimpleServerHandler extends AbstractNettyServerHandler {
    private static final Logger logger = LoggerFactory.getLogger(SimpleServerHandler.class);

    public SimpleServerHandler(Server server) {
        super(server);
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
        Map<String, Channel> channelMap = getServer().getClientChannelMap();
        Collection<Channel> channels = channelMap.values();
        for (Channel ch : channels) {
            if (ch != channel) {
                ch.send(message);
            }
        }
    }

    @Override
    public void caught(Channel channel, Throwable throwable) throws RemoteException {
        if (!(throwable instanceof RemoteException)) {
            logger.error("" + channel, throwable);
        }
    }
}
