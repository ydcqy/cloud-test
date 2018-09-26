package com.ydcqy.ynet.handler;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.channel.NettyChannel;
import com.ydcqy.ynet.server.Server;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.NetUtil;

import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
@ChannelHandler.Sharable
public abstract class AbstractNettyServerHandler extends ChannelDuplexHandler implements Handler {
    private Server server;
    private Handler handler = this;

    public AbstractNettyServerHandler(Server server) {
        this.server = server;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = getOrAddChannelIfConnected(ctx.channel());
        try {
            handler.open(channel);
        } finally {
            removeChannelIfDisconnected(ctx.channel());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = getOrAddChannelIfConnected(ctx.channel());
        try {
            handler.close(channel);
        } finally {
            removeChannelIfDisconnected(ctx.channel());
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = getOrAddChannelIfConnected(ctx.channel());
        try {
            handler.receive(channel, msg);
        } finally {
            removeChannelIfDisconnected(ctx.channel());
        }
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
        Channel channel = getOrAddChannelIfConnected(ctx.channel());
        try {
            handler.send(channel, msg);
        } finally {
            removeChannelIfDisconnected(ctx.channel());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        Channel channel = getOrAddChannelIfConnected(ctx.channel());
        try {
            handler.caught(channel, cause);
        } finally {
            removeChannelIfDisconnected(ctx.channel());
        }
    }

    private com.ydcqy.ynet.channel.Channel getOrAddChannelIfConnected(io.netty.channel.Channel ch) {
        if (ch == null) {
            return null;
        }
        Channel nettyChannel = server.getClientChannelMap().get(NetUtil.toSocketAddressString((InetSocketAddress) ch.remoteAddress()));
        if (null == nettyChannel) {
            nettyChannel = new NettyChannel(ch);
            if (ch.isActive()) {
                server.getClientChannelMap().put(NetUtil.toSocketAddressString((InetSocketAddress) ch.remoteAddress()), nettyChannel);
            }
        }
        return nettyChannel;
    }

    private void removeChannelIfDisconnected(io.netty.channel.Channel ch) {
        if (ch != null && !ch.isActive()) {
            server.getClientChannelMap().remove(NetUtil.toSocketAddressString((InetSocketAddress) ch.remoteAddress()));
        }
    }

    protected final Server getServer() {
        return server;
    }
}
