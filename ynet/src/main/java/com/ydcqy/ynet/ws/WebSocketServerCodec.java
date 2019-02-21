package com.ydcqy.ynet.ws;

import com.ydcqy.ynet.channel.NettyChannel;
import com.ydcqy.ynet.codec.Codec;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author xiaoyu
 */
public final class WebSocketServerCodec extends ChannelInitializer<SocketChannel> implements Codec {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServerCodec.class);

    public WebSocketServerCodec() {
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 设置30秒没有读到数据，则触发一个READER_IDLE事件。
//         pipeline.addLast(new IdleStateHandler(30, 0, 0));
        // HttpServerCodec：将请求和应答消息解码为HTTP消息
        ch.pipeline().addLast("httpCodec", new HttpServerCodec())
                // HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
                .addLast("aggregator", new HttpObjectAggregator(65536))
                // ChunkedWriteHandler：向客户端发送HTML5文件，ChunkedWriteHandler分块写处理，文件过大会将内存撑爆
                .addLast("chunkedWriteHandler", new ChunkedWriteHandler())
                .addLast("fullHttpRequestHandler", new ChannelDuplexHandler() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        if (msg instanceof FullHttpRequest) {
                            FullHttpRequest request = (FullHttpRequest) msg;
                            WebSocketInfo info = new WebSocketInfo();
                            info.setChannel(new NettyChannel(ctx.channel()));
                            info.setUri(request.uri());
                            ctx.channel().attr(Constants.WEB_SOCKET_INFO_ATTRIBUTE_KEY).set(info);
                        }
                        ctx.fireChannelRead(msg);
                    }
                })
                .addLast("webSocketServerProtocolHandler", new WebSocketServerProtocolHandler("/", true))
                // 在管道中添加我们自己的接收数据实现方法
                .addLast("messageToMessageCodec", new MessageToMessageCodec<TextWebSocketFrame, String>() {

                    @Override
                    protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg, List<Object> out) throws Exception {
                        WebSocketRequest request = new WebSocketRequest();
                        request.setWebSocketInfo(ctx.channel().attr(Constants.WEB_SOCKET_INFO_ATTRIBUTE_KEY).get());
                        request.setMessage(msg.text());
                        out.add(request);
                    }

                    @Override
                    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
                        out.add(new TextWebSocketFrame(msg));
                    }
                });
    }

    @Override
    public byte[] encode(Object message) {
        return null;
    }

    @Override
    public Object decode(byte[] bytes) {
        return null;
    }

}

