package com.ydcqy.ynet.server;

import com.ydcqy.ynet.util.Constants;
import com.ydcqy.ynet.util.NamedThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author xiaoyu
 */
public abstract class AbstractNettyServer extends AbstractServer {
    private static final Logger logger = LoggerFactory.getLogger(AbstractNettyServer.class);
    private Channel channel;

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
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1, new NamedThreadFactory("NettyServerBoss", true));
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(Constants.DEFAULT_EVENT_LOOP_THREADS, new NamedThreadFactory("NettyServerWorker", true));

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        logger.info("----initChannel----，管道：{}", pipeline.hashCode());
                        pipeline.addLast("abc", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                logger.info("----channelRead----，msg：{}", ((ByteBuf) msg).toString(Charset.defaultCharset()));
                            }
                        });
                    }
                });
        ChannelFuture channelFuture = bootstrap.bind(getLocalAddress());
        channelFuture.syncUninterruptibly();
        channel = channelFuture.channel();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public int clientCount() {
        return 0;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isSSL() {
        return false;
    }

    @Override
    public boolean isTransportable() {
        return false;
    }
}
