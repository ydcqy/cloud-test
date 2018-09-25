package com.ydcqy.ynet.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

/**
 * @author xiaoyu
 */
public final class SimpleServerCodec extends CombinedChannelDuplexHandler<SimpleServerCodec.RequestDecoder, SimpleServerCodec.ResponseEncoder> implements Codec {

    public SimpleServerCodec() {
        super(new RequestDecoder(), new ResponseEncoder());
    }

    protected static final class RequestDecoder extends ByteToMessageDecoder {

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            out.add(new String("哈哈哈"));
        }
    }

    protected static final class ResponseEncoder extends MessageToByteEncoder {
        @Override
        protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

        }
    }
}
