package com.ydcqy.ymq.producer;

import com.ydcqy.ymq.connection.ConnectionFactory;

/**
 * @author xiaoyu
 */
public abstract class AbstractProducer implements Producer {
    private ConnectionFactory connectionFactory;

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
}
