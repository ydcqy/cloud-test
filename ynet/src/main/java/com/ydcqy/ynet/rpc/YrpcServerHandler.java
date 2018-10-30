package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.exception.RemoteException;
import com.ydcqy.ynet.handler.AbstractNettyServerHandler;
import com.ydcqy.ynet.rpc.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author xiaoyu
 */
class YrpcServerHandler extends AbstractNettyServerHandler {
    private static final Logger logger = LoggerFactory.getLogger(YrpcServerHandler.class);
    private ServerConfig serverConfig;

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
        YrpcRequest request = (YrpcRequest) message;
        Object serviceImpl = serverConfig.getService(request.getInterfaceName(), request.getGroup(), request.getVersion());
        if (logger.isDebugEnabled()) {
            logger.debug("interface: {}, interfaceImpl: {}", request.getInterfaceName(), serviceImpl);
        }
        try {

            Method method = serviceImpl.getClass().getMethod(request.getMethodName(), request.getParam().getClass());
            Object result = method.invoke(serviceImpl, request.getParam());
            if (logger.isDebugEnabled()) {
                logger.debug("interface: {}, interfaceImpl: {}, method: {}, param: {}, execute result: {}"
                        , request.getInterfaceName(), serviceImpl, request.getMethodName(), request.getParam(), result);
            }
        } catch (Exception e) {
            throw new RpcException(e.getMessage(), e);
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

    public void setServerConfig(ServerConfig config) {
        this.serverConfig = config;
    }
}
