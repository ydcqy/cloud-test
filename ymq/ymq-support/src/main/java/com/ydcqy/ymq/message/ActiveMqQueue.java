package com.ydcqy.ymq.message;

/**
 * @author xiaoyu
 */
public class ActiveMqQueue implements Queue {
    private Type type;
    private String queueName;


    public ActiveMqQueue(String queueName, Type type) {
        this.queueName = queueName;
        this.type = type;
    }


    @Override
    public String getQueueName() {
        return queueName;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        QUEUE,
        TOPIC
    }
}
