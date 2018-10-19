package com.ydcqy.ynet.client;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.util.Constants;
import com.ydcqy.ynet.util.NamedThreadFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;

import java.net.InetSocketAddress;

/**
 * @author xiaoyu
 */
public abstract class AbstractNettyClient extends AbstractClient {
    private volatile boolean isTransportable;

    public AbstractNettyClient(String remoteHost, int remotePort) {
        super(remoteHost, remotePort);
    }

    public AbstractNettyClient(InetSocketAddress remoteAddress) {
        super(remoteAddress);
    }

    @Override
    protected void doConnect() {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(Constants.DEFAULT_EVENT_LOOP_THREADS, new NamedThreadFactory("NettyServerWorker", true));
        bootstrap.group(workerGroup)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
//                .channel(NioSocketChannel.class);

    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public void close() {
        super.close();
//        try {
//            channel.close();
//        } catch (IOException e) {
//            logger.warn("Failed to close channel", e);
//        } finally {
//            clientChannels.clear();
//        }
    }

    @Override
    public boolean isSSL() {
        return false;
    }

    @Override
    public boolean isTransportable() {
        return isTransportable;
    }

    @Override
    public InetSocketAddress getLocalAddress() {
        return null;
    }

    @Override
    public Channel getChannel() {
        return null;
    }
}
