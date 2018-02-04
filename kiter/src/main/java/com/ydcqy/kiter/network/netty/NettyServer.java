package com.ydcqy.kiter.network.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServer {
    private byte i = 127;

    public static void main(String[] args) {

        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(50);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(50, new DefaultThreadFactory("kiter"));
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        log.info("管道a" + pipeline.hashCode());
                        pipeline.addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                log.info("----channelActive----");
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("管道b" + ctx.pipeline().hashCode());
                                ByteBuf byteBuf = (ByteBuf) msg;
                                System.out.println(Thread.currentThread().getName() + "----请求----");
                                System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
                            }

                            @Override
                            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("----channelReadComplete----");
                                ctx.writeAndFlush(Unpooled.copiedBuffer("hello world!", CharsetUtil.UTF_8));
                                ctx.close();
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                cause.printStackTrace();
                                ctx.close();
                            }

                        });
                    }
                });
        System.out.println("初始化");
        ChannelFuture channelFuture = bootstrap.bind(8111);
        channelFuture.addListeners(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println(future.isSuccess());
                if (!future.isSuccess()) {
                    future.cause().printStackTrace();
                }
            }
        });
        System.out.println("初始化完成");


    }
}
