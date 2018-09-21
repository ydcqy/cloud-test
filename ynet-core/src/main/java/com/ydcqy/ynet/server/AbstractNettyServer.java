package com.ydcqy.ynet.server;

import com.ydcqy.ynet.util.Constants;
import com.ydcqy.ynet.util.NamedThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author xiaoyu
 */
public abstract class AbstractNettyServer extends AbstractServer {
    private static final Logger logger = LoggerFactory.getLogger(AbstractNettyServer.class);
    private Channel channel;
    private volatile boolean isTransportable;

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
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        logger.info("----initChannel----,{}", ch);
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("decoder", new ByteToMessageDecoder() {
                            @Override
                            protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
                                logger.info("----decode----");
                                logger.info("readableBytes:{},readerIndex:{},in：{}，out：{}", in.readableBytes(), in.readerIndex(), in, out);
                                System.out.println(in.toString(Charset.defaultCharset()));
//                                        ctx.fireChannelRead(in);
                            }

                            @Override
                            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                logger.info("----channelReadComplete11----");
                            }

                        })
                                .addLast("handler", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        logger.info("----channelRead----");
                                        logger.info("msg：{}", msg);
                                        ctx.write(Unpooled.wrappedBuffer("哈哈哈".getBytes()));
                                    }

                                    @Override
                                    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                        logger.info("----channelReadComplete----");
                                        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
                                    }
                                });
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
        channel = channelFuture.syncUninterruptibly().channel();
    }

    @Override
    public void close() {
        super.close();
        channel.close();
    }

    @Override
    public int clientCount() {
        return 0;
    }

    @Override
    public boolean isOpen() {
        return channel.isActive();
    }

    @Override
    public boolean isSSL() {
        return false;
    }

    @Override
    public boolean isTransportable() {
        return isTransportable;
    }
}
