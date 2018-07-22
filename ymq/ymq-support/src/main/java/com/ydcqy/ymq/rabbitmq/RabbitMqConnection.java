package com.ydcqy.ymq.rabbitmq;


import com.rabbitmq.client.Connection;

/**
 * @author xiaoyu
 */
public class RabbitMqConnection implements com.ydcqy.ymq.connection.Connection<Connection> {
    private Connection connection;

    public RabbitMqConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getTargetConnection() {
        return connection;
    }
}
