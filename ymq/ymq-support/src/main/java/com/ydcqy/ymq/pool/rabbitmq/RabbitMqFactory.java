package com.ydcqy.ymq.pool.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.io.IOException;

/**
 * @author xiaoyu
 */
public class RabbitMqFactory implements PooledObjectFactory<PooledConnection> {
    private ConnectionFactory connectionFactory;

    public RabbitMqFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public PooledObject<PooledConnection> makeObject() throws Exception {
        Connection connection = connectionFactory.newConnection();
        PooledConnection pooledConnection = new PooledConnection(connection);
        return new DefaultPooledObject<>(pooledConnection);
    }

    @Override
    public void destroyObject(PooledObject<PooledConnection> p) throws Exception {
        try {
            Connection connection = p.getObject();
            if (connection.isOpen()) {
                connection.close();
            }
        } catch (IOException e) {
        }
    }

    @Override
    public boolean validateObject(PooledObject<PooledConnection> p) {
        try {
            return p.getObject().isOpen();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void activateObject(PooledObject<PooledConnection> p) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<PooledConnection> p) throws Exception {

    }
}
