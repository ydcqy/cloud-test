package com.ydcqy.ymq.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.ydcqy.ymq.message.Message;

/**
 * @author xiaoyu
 */
public class RabbitMqMessage implements Message {
    private Object  obj;
    private byte[]  bytes;
    private Long    delayMillis;
    private Long    periodMillis;
    private Integer repeatNum;

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

    public RabbitMqMessage(Object encodeObj, Long delayMillis, Long periodMillis) {
        this.obj = encodeObj;
        this.delayMillis = delayMillis;
        this.periodMillis = periodMillis;
    }

    public RabbitMqMessage(Object encodeObj, Long delayMillis, Long periodMillis, Integer repeatNum) {
        this.obj = encodeObj;
        this.delayMillis = delayMillis;
        this.periodMillis = periodMillis;
        this.repeatNum = repeatNum;
    }

    @Override
    public byte[] getEncodeContent() {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public Object getDecodeObject() {
        return JSON.parse(bytes);
    }

    @Override
    public Long getDelayMillis() {
        return delayMillis;
    }

    @Override
    public Long getPeriodMillis() {
        return periodMillis;
    }

    @Override
    public Integer getRepeatNum() {
        return repeatNum;
    }
}