package com.ydcqy.kiter.network.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoyu
 */
@Slf4j
public class WsHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("----userEventTriggered----");
        log.info(evt.toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("----channelRead0----");
        log.info("----WS请求---- ", ctx.pipeline().hashCode());
        log.info("####请求内容####");
        System.out.println(msg.text());
        log.info("################");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("----channelReadComplete----");
        for (; ; ) {
            TextWebSocketFrame frame = new TextWebSocketFrame("ws返回结果");
            ctx.writeAndFlush(frame);
            Thread.sleep(100);
        }
    }

}
