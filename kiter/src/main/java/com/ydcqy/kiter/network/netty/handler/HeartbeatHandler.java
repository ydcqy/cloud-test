package com.ydcqy.kiter.network.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lenovo on 2018/2/13.
 */
@Slf4j
@ChannelHandler.Sharable
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {
    private static final ByteBuf HEARTBEAT_SEQ = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(" ", CharsetUtil.ISO_8859_1));

    @Override
    public void userEventTriggered(final ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            ctx.writeAndFlush(HEARTBEAT_SEQ).addListeners(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        log.info("send heartbeat: {}", ctx.channel());
                    } else {
                        log.error("send heartbeat failed: {}", future.cause().getMessage());
                    }
                }
            });
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
