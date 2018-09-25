package com.ydcqy.ynet.handler;

import com.ydcqy.ynet.channel.NettyChannel;
import com.ydcqy.ynet.server.Server;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * @author xiaoyu
 */
@ChannelHandler.Sharable
public abstract class AbstractNettyServerHandler extends ChannelDuplexHandler implements Handler {
    private Server server;

    public AbstractNettyServerHandler(Server server) {
        this.server = server;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        addChannelIfConnected(ctx.channel());
        removeChannelIfDisconnected(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        removeChannelIfDisconnected(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        try {
            cause.printStackTrace();
        } finally {
            removeChannelIfDisconnected(ctx.channel());
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        removeChannelIfDisconnected(ctx.channel());
    }


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
        removeChannelIfDisconnected(ctx.channel());
    }

    private NettyChannel addChannelIfConnected(Channel ch) {
        if (ch == null) {
            return null;
        }
        NettyChannel nettyChannel = new NettyChannel(ch);
        if (ch.isActive()) {
            server.getClientChannels().add(nettyChannel);
        }
        return nettyChannel;
    }

    private void removeChannelIfDisconnected(Channel ch) {
        if (ch != null && !ch.isActive()) {
            server.getClientChannels().remove(new NettyChannel(ch));
        }
    }
}
