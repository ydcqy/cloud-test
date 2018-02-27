package com.ydcqy.kiter.network.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author xiaoyu
 */
@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelDuplexHandler {
    private ConcurrentMap<Channel, NettyChannel> channelMap = new ConcurrentHashMap<>();

    private NettyChannel getOrAddChannel(Channel channel) {
        NettyChannel nettyChannel = channelMap.get(channel);
        if (null == nettyChannel) {
            nettyChannel = NettyChannel.build(channel);
            if (channel.isActive()) {
                channelMap.putIfAbsent(channel, nettyChannel);
            }
        }
        return nettyChannel;
    }

    private void removeChannelIfDisconnected(Channel channel) {
        if (channel != null && !channel.isActive()) {
            channelMap.remove(channel);
        }
    }

    public Collection<NettyChannel> getChannels() {
        return channelMap.values();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("----channelActive---- {}", ctx.channel());
        ctx.fireChannelActive();
        getOrAddChannel(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("----channelInactive---- {}", ctx.channel());
        NettyChannel nettyChannel = channelMap.remove(ctx.channel());
        if (null != nettyChannel) {
            nettyChannel.close();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("----channelRead----");
        NettyChannel nettyChannel = getOrAddChannel(ctx.channel());
        nettyChannel.receive(msg);
        removeChannelIfDisconnected(ctx.channel());
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("----write----");
        NettyChannel nettyChannel = getOrAddChannel(ctx.channel());
        nettyChannel.send(msg);
        removeChannelIfDisconnected(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        removeChannelIfDisconnected(ctx.channel());
        log.error(cause.getMessage(), cause);
    }
}
