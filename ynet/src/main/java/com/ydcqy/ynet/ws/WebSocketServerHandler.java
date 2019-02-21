package com.ydcqy.ynet.ws;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.exception.RemoteException;
import com.ydcqy.ynet.handler.AbstractNettyServerHandler;
import com.ydcqy.ynet.util.NamedThreadFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoyu
 */
class WebSocketServerHandler extends AbstractNettyServerHandler {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServerHandler.class);
    private WebSocketRequestHandler handler;
    private ExecutorService executorService = new ThreadPoolExecutor(100, 100,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new NamedThreadFactory("ws"));

    public WebSocketServerHandler() {
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            ctx.pipeline().remove("fullHttpRequestHandler");
            handler.onOpen(ctx.channel().attr(Constants.WEB_SOCKET_INFO_ATTRIBUTE_KEY).get());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        handler.onClose(ctx.channel().attr(Constants.WEB_SOCKET_INFO_ATTRIBUTE_KEY).get());
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
        if (message instanceof WebSocketRequest) {
            WebSocketRequest request = (WebSocketRequest) message;
            handler.onMessag(request.getWebSocketInfo(), request.getMessage());
        }
    }

    @Override
    public void caught(Channel channel, Throwable cause) throws RemoteException {
        logger.error("" + channel, cause);
        try {
            channel.close();
        } catch (Throwable e) {
        }
    }

    public void setHandler(WebSocketRequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public ExecutorService getExecutorService() {
        return executorService;
    }
}
