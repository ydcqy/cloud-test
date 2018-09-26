package com.ydcqy.ynet.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.NetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author xiaoyu
 */
public final class SimpleServerCodec extends CombinedChannelDuplexHandler<SimpleServerCodec.RequestDecoder, SimpleServerCodec.ResponseEncoder> implements Codec {
    private static final Logger logger = LoggerFactory.getLogger(SimpleServerCodec.class);

    public SimpleServerCodec() {
        super(new RequestDecoder(), new ResponseEncoder());
    }

    protected static final class RequestDecoder extends ByteToMessageDecoder {

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            int readableBytes = in.readableBytes();
            if (readableBytes > 0) {
                ByteBuf byteBuf = in.readBytes(readableBytes);
                String str = byteBuf.toString(Charset.defaultCharset());
                out.add(str);
            }
        }
    }

    protected static final class ResponseEncoder extends MessageToByteEncoder {
        @Override
        protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
            out.writeBytes(((String) msg).getBytes());
        }
    }
}
