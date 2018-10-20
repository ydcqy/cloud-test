package com.ydcqy.ynet.server;

import com.ydcqy.ynet.channel.Channel;
import com.ydcqy.ynet.channel.NettyChannel;
import com.ydcqy.ynet.util.Constants;
import com.ydcqy.ynet.util.NamedThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

/**
 * @author xiaoyu
 */
public abstract class AbstractNettyServer extends AbstractServer {
    private static final Logger logger = LoggerFactory.getLogger(AbstractNettyServer.class);
    private Channel channel;
    private volatile boolean isTransportable;
    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;
    private ServerBootstrap bootstrap;

    public AbstractNettyServer(int port) {
        super(port);
    }

    public AbstractNettyServer(String host, int port) {
        super(host, port);
    }

    public AbstractNettyServer(InetSocketAddress bindAddress) {
        super(bindAddress);
    }

    @Override
    protected void doBind() {
        bossGroup = new NioEventLoopGroup(1, new NamedThreadFactory("NettyServerBoss", true));
        workerGroup = new NioEventLoopGroup(Constants.DEFAULT_EVENT_LOOP_THREADS, new NamedThreadFactory("NettyServerWorker", true));

        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("codec", (ChannelHandler) getCodec())
                                .addLast("handler", (ChannelHandler) getHandler());
                    }
                });
        ChannelFuture channelFuture = bootstrap.bind(getLocalAddress());
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    isTransportable = true;
                }
            }
        });
        channel = new NettyChannel(channelFuture.syncUninterruptibly().channel());
    }

    @Override
    public void close() {
        super.close();
        try {
            if (channel != null) {
                channel.close();
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        } catch (IOException e) {
            logger.warn("Failed to close channel", e);
        } finally {
            getHandler().getChannelMap().clear();
        }
    }

    @Override
    public boolean isOpen() {
        return channel.isOpen();
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
    public Channel getChannel() {
        return channel;
    }

    @Override
    public Map<String, Channel> getClientChannelMap() {
        return getHandler().getChannelMap();
    }
}