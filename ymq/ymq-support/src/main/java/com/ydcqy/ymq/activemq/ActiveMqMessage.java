package com.ydcqy.ymq.activemq;

import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.util.ProtobufUtils;

/**
 * @author xiaoyu
 */
public class ActiveMqMessage implements Message {
    private Object obj;
    private byte[] bytes;
    private Long   delayMillis;


    public ActiveMqMessage(Object encodeObj) {
        this.obj = encodeObj;
    }

    public ActiveMqMessage(byte[] decodeBytes) {
        this.bytes = decodeBytes;
    }

    public ActiveMqMessage(Object encodeObj, Long delayMillis) {
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
