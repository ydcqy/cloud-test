package com.ydcqy.ymq.rabbitmq;

import com.ydcqy.ymq.message.Queue;

/**
 * @author xiaoyu
 */
public class RabbitMqQueue implements Queue {
    private Type   type;
    private String queueName;


    public RabbitMqQueue(String queueName, Type type) {
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

    public int hashCode() {
        int h;
        return (h = queueName.hashCode()) ^ (h >> 16) ^ type.hashCode();
    }

    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        RabbitMqQueue n = (RabbitMqQueue) obj;
        return type.equals(n.getType()) && queueName.equals(n.queueName);
    }

    public enum Type {
        QUEUE,
        TOPIC
    }
}
