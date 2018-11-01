package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.exception.RemoteException;
import com.ydcqy.ynet.handler.AbstractNettyClientHandler;
import com.ydcqy.ynet.util.ResultSynchronizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

/**
 * @author xiaoyu
 */
class YrpcClientHandler extends AbstractNettyClientHandler {
    private static final Logger logger = LoggerFactory.getLogger(YrpcClientHandler.class);

    public YrpcClientHandler() {
    }

    @Override
    public ExecutorService getExecutorService() {
        return null;
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
        YrpcResponse response = (YrpcResponse) message;
        ResultSynchronizer.set(response.getRequestId(), response, 5000);
    }

    @Override
    public void caught(Channel channel, Throwable cause) throws RemoteException {
        logger.error("" + channel, cause);
    }
}
