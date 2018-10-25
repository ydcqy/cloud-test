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
public final class YrpcServerCodec extends CombinedChannelDuplexHandler<ByteToMessageDecoder, MessageToByteEncoder> implements Codec {
    private static final Logger logger = LoggerFactory.getLogger(YrpcServerCodec.class);

    public YrpcServerCodec() {
        init(new RequestDecoder(), new ResponseEncoder());
    }

    @Override
    public byte[] encode(Object message) {
        return new byte[0];
    }

    @Override
    public Object decode(byte[] bytes) {
        return null;
    }

    private final class RequestDecoder extends ByteToMessageDecoder {
        private Codec codec = YrpcServerCodec.this;

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            if (logger.isDebugEnabled()) {
                logger.debug("-----decode before----- in: {},out: {},inObj: {},outObj: {}", in, out, System.identityHashCode(in), System.identityHashCode(out));
            }
            int readableBytes = in.readableBytes();
            if (readableBytes > 0) {
                ByteBuf byteBuf = in.readBytes(readableBytes);
                String str = byteBuf.toString(Charset.defaultCharset());
                out.add(str);
            }
            long i = 0x111;
            if (logger.isDebugEnabled()) {
                logger.debug("-----decode after----- in: {},out: {},inObj: {},outObj: {}", in, "", System.identityHashCode(in), System.identityHashCode(out));
            }
        }
    }

    private final class ResponseEncoder extends MessageToByteEncoder {
        private Codec codec = YrpcServerCodec.this;

        @Override
        protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
            if (logger.isDebugEnabled()) {
                logger.debug("-----encode before----- msg: {},out: {},msgObj: {},outObj: {}", msg, out, System.identityHashCode(msg), System.identityHashCode(out));
            }
            out.writeBytes(((String) msg).getBytes());
            if (logger.isDebugEnabled()) {
                logger.debug("-----encode after----- msg: {},out: {},msgObj: {},outObj: {}", msg, out, System.identityHashCode(msg), System.identityHashCode(out));
            }
        }
    }
}

