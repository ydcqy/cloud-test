package com.ydcqy.ynet.rpc;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.ydcqy.ynet.codec.Codec;
import com.ydcqy.ynet.exception.CodecException;
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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
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
        private static final int HEAD_BYTE_COUNT = 5;
        private Codec codec = YrpcServerCodec.this;
        private volatile boolean isEncode;
        private volatile int encodeLength;
        private volatile SerializationType serializationType;


        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            if (logger.isDebugEnabled()) {
                logger.debug("-----decode before----- in: {},out: {},inObj: {},outObj: {}", in, out, System.identityHashCode(in), System.identityHashCode(out));
            }
            while (in.readableBytes() > 0) {
                if (isEncode) {
                    if (in.readableBytes() >= encodeLength) {
                        ByteBuf byteBuf = in.readBytes(encodeLength);
                        YrpcRequest req = null;
                        switch (serializationType) {
                            case PROTO:
                                YrpcProtos.YrpcRequest req1 = YrpcProtos.YrpcRequest.parseFrom(byteBuf.nioBuffer());
                                req = new YrpcRequest();
                                if (!StringUtil.isNullOrEmpty(req1.getRequestId())) {
                                    req.setRequestId(req1.getRequestId());
                                }
                                if (!StringUtil.isNullOrEmpty(req1.getGroup())) {
                                    req.setGroup(req1.getGroup());
                                }
                                if (!StringUtil.isNullOrEmpty(req1.getVersion())) {
                                    req.setVersion(req1.getVersion());
                                }
                                if (!StringUtil.isNullOrEmpty(req1.getInterfaceName())) {
                                    req.setInterfaceName(req1.getInterfaceName());
                                }
                                if (!StringUtil.isNullOrEmpty(req1.getMethodName())) {
                                    req.setMethodName(req1.getMethodName());
                                }
                                Method[] methods = Class.forName(req.getInterfaceName()).getMethods();
                                if (methods != null && methods.length > 0) {
                                    //proto check
                                    Method method = null;
                                    for (Method m : methods) {
                                        if (m.getName().equals(req.getMethodName())) {
                                            if (method != null) {
                                                throw new CodecException(req.getRequestId(), "The " + req.getInterfaceName() + " has more than one " + req.getMethodName() + " method when serializationType is proto");
                                            }
                                            method = m;
                                        }
                                    }

                                    List<ByteString> paramsList = req1.getParamsList();
                                    if (paramsList != null && paramsList.size() > 0) {
                                        Class<?>[] parameterTypes = method.getParameterTypes();
                                        if ((parameterTypes == null && paramsList != null)
                                                || (parameterTypes != null && paramsList == null)
                                                || (parameterTypes.length != paramsList.size())) {
                                            throw new CodecException(req.getRequestId(), "The " + req.getInterfaceName() + "#" + req.getMethodName() + " signature cannot match when serializationType is proto");
                                        }
                                        if (parameterTypes != null && parameterTypes.length > 0) {
                                            List<Object> params = new ArrayList<>();
                                            Iterator<ByteString> paramsIterator = paramsList.iterator();
                                            for (Class<?> parameterType : parameterTypes) {
                                                if (!MessageLiteOrBuilder.class.isAssignableFrom(parameterType)) {
                                                    throw new CodecException(req.getRequestId(), "All the Params of " + req.getInterfaceName() + "#" + req.getMethodName() + " must be MessageLiteOrBuilder when serializationType is proto");
                                                }
                                                Class<?> parameterClass = parameterType;
                                                ByteString param = paramsIterator.next();
                                                if (MessageLite.class.isAssignableFrom(parameterClass)) {
                                                    params.add(((MessageLiteOrBuilder) parameterClass
                                                            .getMethod("newBuilder").invoke(null))
                                                            .getDefaultInstanceForType().getParserForType()
                                                            .parseFrom(param));
                                                } else if (MessageLite.Builder.class.isAssignableFrom(parameterClass)) {
                                                    Constructor<?> constructor = parameterClass.getDeclaredConstructor();
                                                    constructor.setAccessible(true);
                                                    params.add(((MessageLite.Builder) constructor.newInstance()).mergeFrom(param));
                                                }
                                            }
                                            req.setParams(params.toArray());
                                        }
                                    }
                                }
                                break;
                            case THRIFT:
                                throw new UnsupportedOperationException();
                            case JSON:
                                req = JSON.parseObject(byteBuf.toString(Charset.defaultCharset()), YrpcRequest.class);
                                break;
                            case JAVA:
                                byte[] bytes = new byte[byteBuf.readableBytes()];
                                byteBuf.readBytes(bytes);
                                req = (YrpcRequest) SerializationUtils.deserialize(bytes);
                                break;
                            default:
                                throw new UnsupportedOperationException();
                        }

                        serializationType = null;
                        isEncode = false;
                        encodeLength = 0;
                        out.add(req);
                        continue;
                    }
                    break;
                } else {
                    if (in.readableBytes() >= HEAD_BYTE_COUNT) {
                        byte typeBit = in.readByte();
                        SerializationType type = SerializationType.valueOf(typeBit);
                        if (type == null) {
                            in.resetReaderIndex();
                            in.resetWriterIndex();
                            throw new UnsupportedOperationException("Unsupported protocol");
                        }
                        int lenValue = 0;
                        lenValue += ((in.readByte() & 0xff) << 24);
                        lenValue += ((in.readByte() & 0xff) << 16);
                        lenValue += ((in.readByte() & 0xff) << 8);
                        lenValue += (in.readByte() & 0xff);

                        serializationType = type;
                        isEncode = true;
                        encodeLength = lenValue;
                        continue;
                    }
                    break;
                }
            }
            if (logger.isDebugEnabled() && !out.isEmpty()) {
                logger.debug("-----decode after----- in: {},out: {},inObj: {},outObj: {}", in, out.size(), System.identityHashCode(in), System.identityHashCode(out));
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

