package com.ydcqy.ymq.kafka;

import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.util.ProtobufUtils;

/**
 * @author xiaoyu
 */
public class KafkaMessage implements Message {
    private Object obj;
    private byte[] bytes;


    public KafkaMessage(Object encodeObj) {
        this.obj = encodeObj;
    }

    public KafkaMessage(byte[] decodeBytes) {
        this.bytes = decodeBytes;
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
        return null;
    }
}
