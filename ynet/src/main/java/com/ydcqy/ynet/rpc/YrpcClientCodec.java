package com.ydcqy.ynet.rpc;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.request.Request;
import com.ydcqy.ynet.rpc.proto.YrpcProtos;
import com.ydcqy.ynet.util.SerializationType;
import com.ydcqy.ynet.util.SerializationUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author xiaoyu
 */
final class YrpcClientCodec extends CombinedChannelDuplexHandler<ByteToMessageDecoder, MessageToByteEncoder> implements Codec {
    private static final Logger logger = LoggerFactory.getLogger(YrpcClientCodec.class);
    private SerializationType serializationType;

    public YrpcClientCodec(SerializationType serializationType) {
        this.serializationType = serializationType;
        init(new ResponseDecoder(), new RequestEncoder());
    }

    @Override
    public byte[] encode(Object message) {
        if (null == message) {
            return null;
        }
        if (!(message instanceof YrpcRequest)) {
            throw new UnsupportedOperationException("Unsupported type of " + message.getClass());
        }
        YrpcRequest req = (YrpcRequest) message;
        byte[] bytes;
        switch (serializationType) {
            case PROTO:
                Object param = req.getParam();
                if (param != null && !(param instanceof MessageLiteOrBuilder)) {
                    throw new UnsupportedOperationException("The param must be MessageLiteOrBuilder");
                }
                YrpcProtos.YrpcRequest.Builder builder = YrpcProtos.YrpcRequest.newBuilder();
                if (!StringUtil.isNullOrEmpty(req.getRequestId())) {
                    builder.setRequestId(req.getRequestId());
                }
                if (!StringUtil.isNullOrEmpty(req.getGroup())) {
                    builder.setGroup(req.getGroup());
                }
                if (!StringUtil.isNullOrEmpty(req.getVersion())) {
                    builder.setVersion(req.getVersion());
                }
                if (!StringUtil.isNullOrEmpty(req.getInterfaceName())) {
                    builder.setInterfaceName(req.getInterfaceName());
                }
                if (!StringUtil.isNullOrEmpty(req.getMethodName())) {
                    builder.setMethodName(req.getMethodName());
                }
                if (param instanceof MessageLite) {
                    builder.setParam(ByteString.copyFrom(((MessageLite) param).toByteArray()));
                } else if (param instanceof MessageLite.Builder) {
                    builder.setParam(ByteString.copyFrom(((MessageLite.Builder) param).build().toByteArray()));
                }
                YrpcProtos.YrpcRequest req1 = builder.build();
                bytes = req1.toByteArray();
                break;
            case THRIFT:
                throw new UnsupportedOperationException();
            case JSON:
                bytes = JSON.toJSONString(req).getBytes();
                break;
            case JAVA:
                bytes = SerializationUtils.serialize(req);
                break;

            default:
                throw new UnsupportedOperationException();
        }
        return bytes;
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
                logger.debug("-----decode before----- in: {},out: {},inObj: {},outObj: {}", in, out, System.identityHashCode(in), System.identityHashCode(out));
            }
            int readableBytes = in.readableBytes();
            if (readableBytes > 0) {
                ByteBuf byteBuf = in.readBytes(readableBytes);
                String str = byteBuf.toString(Charset.defaultCharset());
                out.add(str);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("-----decode after----- in: {},out: {},inObj: {},outObj: {}", in, out, System.identityHashCode(in), System.identityHashCode(out));
            }
        }
    }

    private final class RequestEncoder extends MessageToByteEncoder {
        private YrpcClientCodec codec = YrpcClientCodec.this;

        @Override
        protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
            if (logger.isDebugEnabled()) {
                logger.debug("-----encode before----- msg: {},out: {},msgObj: {},outObj: {}", msg, out, System.identityHashCode(msg), System.identityHashCode(out));
            }
            if (!(msg instanceof Request)) {
                throw new IllegalArgumentException("The message type must be Request.class");
            }
            ByteBuf buf = ctx.alloc().buffer();
            byte[] bytes = codec.encode(msg);
            int length = bytes.length;
            buf.writeByte(serializationType.bitValue);
            buf.writeByte((length >> 24) & 0xff);
            buf.writeByte((length >> 16) & 0xff);
            buf.writeByte((length >> 8) & 0xff);
            buf.writeByte(length & 0xff);
            out.writeBytes(buf.writeBytes(bytes));
            if (logger.isDebugEnabled()) {
                logger.debug("-----encode after----- msg: {},out: {},msgObj: {},outObj: {}", msg, out, System.identityHashCode(msg), System.identityHashCode(out));
            }
        }

    }
}
