package com.ydcqy.kiter.network.netty;

import com.ydcqy.kiter.network.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
@Slf4j
public class NettyChannel extends AbstractChannel {
    private Channel channel;

    private void setChannel(Channel channel) {
        this.channel = channel;
    }

    private NettyChannel() {

    }

    public static NettyChannel build(Channel channel) {
        NettyChannel nettyChannel = new NettyChannel();
        nettyChannel.setChannel(channel);
        return nettyChannel;
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
    public Boolean isConnected() {
        return channel.isActive();
    }

    @Override
    public void send(Object msg) {
        log.info("----send---- {}", msg.getClass());
    }

    @Override
    public void receive(Object msg) {
        log.info("----receive---- {}", msg.getClass());
    }

    @Override
    public void close() throws IOException {
        if (isConnected()) {
            channel.close().addListeners(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        log.error("Failed to close channel", future.cause());
                    }
                }
            });
        }
    }
}
