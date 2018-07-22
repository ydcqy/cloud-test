package com.ydcqy.ymq.producer;

import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.message.Queue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author xiaoyu
 */
public abstract class AbstractProducer implements Producer {
    private ConnectionFactory connectionFactory;

    private ConcurrentMap<Queue, Boolean> queues = new ConcurrentHashMap<>();


    public AbstractProducer() {

    }

    public AbstractProducer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }


    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    protected boolean isQueueInitialized(Queue queue) {
        return queues.containsKey(queue);
    }

    @Override
    public void send(Queue queue, Message msg) throws MqException {
        doSend(queue, msg);
        queues.putIfAbsent(queue, Boolean.TRUE);
    }

    protected abstract void doSend(Queue queue, Message msg) throws MqException;
}
