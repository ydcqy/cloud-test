package com.ydcqy.ymq.consumer;

import com.ydcqy.ymq.activemq.ActiveMqConfiguration;
import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.message.MessageListener;
import com.ydcqy.ymq.message.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaoyu
 */
public abstract class AbstractConsumer implements Consumer {
    private ConnectionFactory connectionFactory;
    private ConcurrentHashMap<Queue, List<MessageListener>> queueListener = new ConcurrentHashMap<Queue, List<MessageListener>>();

    public AbstractConsumer() {

    }

    public AbstractConsumer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }


    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Consumer bindMessageListener(Queue queue, MessageListener listener) {
        ActiveMqConfiguration configuration = (ActiveMqConfiguration) connectionFactory.getConfiguration();
        return this.bindMessageListener(queue, configuration.getConsumerListener().getConcurrency(), listener);
    }

    @Override
    public Consumer bindMessageListener(Queue queue, Integer consumerConcurrency, MessageListener listener) {
        if (queueListener.containsKey(queue)) {
            throw new RuntimeException("Please do not repeat the binding messageListener");
        }
        List<MessageListener> listeners = new ArrayList<>();
        for (; consumerConcurrency-- > 0; ) {
            listeners.add(listener);
        }
        if (listeners.size() > 0) {
            queueListener.put(queue, listeners);
        }
        return this;
    }

    @Override
    public MessageListener getMessageListener(Queue queue) {
        List<MessageListener> listeners;
        return (listeners = queueListener.get(queue)) != null ? listeners.get(0) : null;
    }

    protected ConcurrentHashMap<Queue, List<MessageListener>> getQueueListener() {
        return queueListener;
    }
}
