package com.ydcqy.ymq.activemq;

import com.alibaba.fastjson.JSON;
import com.ydcqy.ymq.message.Message;

import java.nio.charset.Charset;

/**
 * @author xiaoyu
 */
public class ActiveMqMessage implements Message {
    private Object obj;
    private byte[] bytes;
    private Long delayMillis;
    private Long periodMillis;
    private Integer repeatNum;

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

    public ActiveMqMessage(Object encodeObj, Long delayMillis, Long periodMillis) {
        this.obj = encodeObj;
        this.delayMillis = delayMillis;
        this.periodMillis = periodMillis;
    }

    public ActiveMqMessage(Object encodeObj, Long delayMillis, Long periodMillis, Integer repeatNum) {
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
