package com.ydcqy.ymq.rabbitmq;

import com.ydcqy.ymq.message.Queue;

/**
 * @author xiaoyu
 */
public class RabbitMqQueue implements Queue {
    private static final String DEFAULT_EXCHANGE_NAME = "ymq.default";
    private String messageRoutingKey;
    private String exchangeName;
    private String queueBindingKey;
    private String queueName;

    public RabbitMqQueue(String queueName) {
        this.messageRoutingKey = queueName;
        this.queueBindingKey = queueName;
        this.queueName = queueName;
        this.exchangeName = DEFAULT_EXCHANGE_NAME;
    }


    @Override
    public String getQueueName() {
        return queueName;
    }

    public String getMessageRoutingKey() {
        return messageRoutingKey;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getQueueBindingKey() {
        return queueBindingKey;
    }

    public int hashCode() {
        int h;
        return ((h = messageRoutingKey.hashCode()) ^ (h >> 16)) ^
                ((h = exchangeName.hashCode()) ^ (h >> 16)) ^
                ((h = queueBindingKey.hashCode()) ^ (h >> 16)) ^
                ((h = queueName.hashCode()) ^ (h >> 16));
    }

    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        RabbitMqQueue n = (RabbitMqQueue) obj;
        return messageRoutingKey.equals(n.messageRoutingKey) &&
                exchangeName.equals(n.exchangeName) &&
                queueBindingKey.equals(n.queueBindingKey) &&
                queueName.equals(n.queueName);
    }


}
