package com.ydcqy.kiter.network.netty.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoyu
 */
@Slf4j
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        log.info("----channelRead0----");
        log.info("####请求内容####");
        System.out.println(request.toString());
        log.info("################");
        HttpHeaders headers = request.headers();
        boolean isWs = headers.contains("Upgrade", "websocket", Boolean.TRUE);
        if (isWs) {
            ctx.fireChannelRead(request.retain());
        } else {
            log.info("----HTTP请求---- ", ctx.pipeline().hashCode());
            returnHttpResponse(ctx, request);
            boolean isKeepAlive = headers.contains("Connection", "keep-alive", Boolean.TRUE);
            if (!isKeepAlive) {
                ctx.close();
            }
        }
    }

    private void returnHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request) {
        FullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(),
                HttpResponseStatus.OK, Unpooled.copiedBuffer("http返回结果", CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        ctx.writeAndFlush(response);
        ctx.close();
    }
}