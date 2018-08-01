package com.ydcqy.ymq.pool.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.ydcqy.ymq.exception.PooledException;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xiaoyu
 */
public class PooledConnectionFactory extends ConnectionFactory {
    private GenericObjectPool<PooledConnection> connectionsPool;
    private ConnectionFactory                   connectionFactory;

    public PooledConnectionFactory(GenericObjectPoolConfig poolConfig, ConnectionFactory cf) {
        connectionFactory = cf;
        connectionsPool = new GenericObjectPool<PooledConnection>(new RabbitMqFactory(connectionFactory), poolConfig);
    }

    @Override
    public Connection newConnection() throws IOException, TimeoutException {
        try {
            PooledConnection connection = connectionsPool.borrowObject();
            connection.setConnectionsPool(connectionsPool);
            return connection;
        } catch (Exception e) {
            throw new PooledException("Could not get a connection from the pool", e);
        }
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
}
