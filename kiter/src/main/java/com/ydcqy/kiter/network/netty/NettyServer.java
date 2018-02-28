package com.ydcqy.kiter.network.netty;


import com.ydcqy.kiter.network.AbstractServer;
import com.ydcqy.kiter.network.RemoteException;
import com.ydcqy.kiter.network.netty.ws.WebSocketCodec;
import com.ydcqy.kiter.util.NamedThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;

@Slf4j
public class NettyServer extends AbstractServer {
    private Channel channel;
    private Collection<NettyChannel> channels;

    public NettyServer(InetSocketAddress bindAddress) throws RemoteException {
        super(bindAddress);
    }

    @Override
    protected void doOpen() throws Throwable {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1, new NamedThreadFactory("NettyServerBoss", true));
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(3, new NamedThreadFactory("NettyServerWorker", true));
        final NioEventLoopGroup webSocketGroup = new NioEventLoopGroup(3, new NamedThreadFactory("WebSocket", true));
        final NioEventLoopGroup webSocketGroup1 = new NioEventLoopGroup(3, new NamedThreadFactory("WebSocket1", true));
        final NettyServerHandler nettyServerHandler = new NettyServerHandler();

        channels = nettyServerHandler.getChannels();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    private WebSocketCodec webSocketCodec = new WebSocketCodec();
                    private WebSocketCodec.TextWebSocketFrameHandler textWebSocketFrameHandler = new WebSocketCodec.TextWebSocketFrameHandler();

                    private void initWebSocket(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        log.info("----initChannel----，管道：{}", pipeline.hashCode());
                        pipeline.addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(Integer.MAX_VALUE))
                                .addLast(new ChunkedWriteHandler())
                                .addLast(webSocketGroup, webSocketCodec)
                                .addLast(new WebSocketServerProtocolHandler("/"))
                                .addLast(webSocketGroup1,textWebSocketFrameHandler)
//                                .addLast("handler", nettyServerHandler)
                        ;
                    }

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        initWebSocket(ch);

                    }
                });
        log.info("初始化");
        ChannelFuture channelFuture = bootstrap.bind(getBindAddress());
        channelFuture.addListeners(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    future.cause().printStackTrace();
                }
            }
        });
        this.channel = channelFuture.syncUninterruptibly().channel();
        log.info("初始化完成");
    }

    @Override
    public boolean isBound() {
        return channel.isActive();
    }

    @Override
    public Collection<com.ydcqy.kiter.network.Channel> getChannels() {
        Collection<com.ydcqy.kiter.network.Channel> rs = new HashSet<com.ydcqy.kiter.network.Channel>();
        for (NettyChannel nettyChannel : channels) {
            if (nettyChannel.isConnected()) {
                rs.add(nettyChannel);
            } else {
                channels.remove(nettyChannel);
            }
        }
        return null;
    }

    @Override
    public com.ydcqy.kiter.network.Channel getChannel(InetSocketAddress remoteAddress) {
        return null;
    }
}
