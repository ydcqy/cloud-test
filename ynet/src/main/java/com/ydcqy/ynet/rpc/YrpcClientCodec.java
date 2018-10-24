package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.codec.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author xiaoyu
 */
final class YrpcClientCodec extends CombinedChannelDuplexHandler<ByteToMessageDecoder, MessageToByteEncoder> implements Codec {
    private static final Logger logger = LoggerFactory.getLogger(YrpcClientCodec.class);

    public YrpcClientCodec() {
        init(new ResponseDecoder(), new RequestEncoder());
    }

    @Override
    public byte[] encode(Object message) {
        return "哈哈".getBytes();
    }

    @Override
    public Object decode(byte[] bytes) {
        return null;
    }

    private final class ResponseDecoder extends ByteToMessageDecoder {
        private Codec codec = YrpcClientCodec.this;

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            if (logger.isDebugEnabled()) {
                logger.info("-----decode before----- in: {},out: {},inObj: {},outObj: {}", in, out, System.identityHashCode(in), System.identityHashCode(out));
            }
            int readableBytes = in.readableBytes();
            if (readableBytes > 0) {
                ByteBuf byteBuf = in.readBytes(readableBytes);
                String str = byteBuf.toString(Charset.defaultCharset());
                out.add(str);
            }
            if (logger.isDebugEnabled()) {
                logger.info("-----decode after----- in: {},out: {},inObj: {},outObj: {}", in, out, System.identityHashCode(in), System.identityHashCode(out));
            }
        }
    }

    private final class RequestEncoder extends MessageToByteEncoder {
        private Codec codec = YrpcClientCodec.this;

        @Override
        protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
            if (logger.isDebugEnabled()) {
                logger.info("-----encode before----- msg: {},out: {},msgObj: {},outObj: {}", msg, out, System.identityHashCode(msg), System.identityHashCode(out));
            }
            ByteBuf buf = ctx.alloc().buffer();
            byte[] bytes = codec.encode(msg);
            int length = bytes.length;
            String len = Integer.toBinaryString(length);
            out.writeBytes(buf.writeBytes(bytes));
            if (logger.isDebugEnabled()) {
                logger.info("-----encode after----- msg: {},out: {},msgObj: {},outObj: {}", msg, out, System.identityHashCode(msg), System.identityHashCode(out));
            }
        }
    }
}
