package com.ydcqy.ynet.channel;

import io.netty.channel.ChannelFuture;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public class NettyChannel implements Channel {
    private io.netty.channel.Channel channel;

    public NettyChannel(io.netty.channel.Channel channel) {
        this.channel = channel;
    }

    @Override
    public void close() throws IOException {
        channel.close();
    }

    @Override
    public boolean isOpen() {
        return channel.isActive();
    }

    @Override
    public void send(Object message) {
        ChannelFuture future = channel.writeAndFlush(message);

    }

    @Override
    public InetSocketAddress getLocalAddress() {
        return (InetSocketAddress) channel.localAddress();
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return (InetSocketAddress) channel.remoteAddress();
    }

    @Override
    public int hashCode() {
        return channel.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        return channel.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "NettyChannel [channel=" + channel + "]";
    }

}
