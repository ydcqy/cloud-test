package com.ydcqy.ynet.handler;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.exception.RemoteException;
import com.ydcqy.ynet.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    }

    @Override
    public void close(Channel channel) throws RemoteException {

    }

    @Override
    public void send(Channel channel, Object message) throws RemoteException {

    }

    @Override
    public void receive(Channel channel, Object message) throws RemoteException {

    }

    @Override
    public void caught(Channel channel, Throwable throwable) throws RemoteException {

    }
}
