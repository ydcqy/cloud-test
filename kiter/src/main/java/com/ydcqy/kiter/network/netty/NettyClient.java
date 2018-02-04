package com.ydcqy.kiter.network.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * Created by lenovo on 2018/1/31.
 */
@Slf4j
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup(50);
        bootstrap.group(group)
                .channel(NioSocketChannel.class)

                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("管道a" + ch.pipeline().hashCode());
                        ch.pipeline().addLast("RpcEncode", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("----channelActive----");
                                ctx.writeAndFlush(Unpooled.copiedBuffer("Hi server,I'm client!",
                                        CharsetUtil.UTF_8));
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("----channelRead----");
                                ByteBuf byteBuf = (ByteBuf) msg;
                                System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                cause.printStackTrace();
                                ctx.close();
                            }
                        });
                    }
                })
        ;
        System.out.println("初始化");
        for (int i = 9; i < 1000; i++) {
            bootstrap.connect(new InetSocketAddress("127.0.0.1", 8111)).
                    addListeners(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            log.info("连接结果：{}", String.valueOf(future.isSuccess()));
                            if (!future.isSuccess()) {
                                future.cause().printStackTrace();
                            }
                        }
                    });

        }

    }
}
