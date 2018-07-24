package com.ydcqy.ymq.rabbitmq;

import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.util.ProtobufUtils;

/**
 * @author xiaoyu
 */
public class RabbitMqMessage implements Message {
    private Object obj;
    private byte[] bytes;
    private Long   delayMillis;


    public RabbitMqMessage(Object encodeObj) {
        this.obj = encodeObj;
    }

    public RabbitMqMessage(byte[] decodeBytes) {
        this.bytes = decodeBytes;
    }

    public RabbitMqMessage(Object encodeObj, Long delayMillis) {
        this.obj = encodeObj;
        this.delayMillis = delayMillis;
    }

    @Override
    public byte[] getEncodeContent() {
        return ProtobufUtils.serialize(obj);
    }

    @Override
    public <T> T getDecodeObject(Class<T> type) {
        return ProtobufUtils.deserialize(bytes, type);
    }

    @Override
    public Long getDelayMillis() {
        return delayMillis;
    }


}
