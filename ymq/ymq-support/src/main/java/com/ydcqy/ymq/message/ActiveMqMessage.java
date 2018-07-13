package com.ydcqy.ymq.message;

import com.alibaba.fastjson.JSON;

import java.nio.charset.Charset;

/**
 * @author xiaoyu
 */
public class ActiveMqMessage implements Message {
    private Object obj;
    private Long delayMillis;
    private Long periodMillis;
    private Integer repeatNum;

    public ActiveMqMessage(Object obj) {
        this.obj = obj;
    }

    public ActiveMqMessage(Object obj, String queueName, ActiveMqQueue.Type type, Long delayMillis) {
        this.obj = obj;
        this.delayMillis = delayMillis;
    }

    public ActiveMqMessage(Object obj, String queueName, ActiveMqQueue.Type type, Long delayMillis, Long periodMillis) {
        this.obj = obj;
        this.delayMillis = delayMillis;
        this.periodMillis = periodMillis;
    }

    public ActiveMqMessage(Object obj, String queueName, ActiveMqQueue.Type type, Long delayMillis, Long periodMillis, Integer repeatNum) {
        this.obj = obj;
        this.delayMillis = delayMillis;
        this.periodMillis = periodMillis;
        this.repeatNum = repeatNum;
    }

    @Override
    public byte[] getContent() {
        return JSON.toJSONString(obj).getBytes(Charset.forName("utf-8"));
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
