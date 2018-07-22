package com.ydcqy.ymq.rabbitmq;

import com.ydcqy.ymq.configuration.Configuration;
import com.ydcqy.ymq.connection.Connection;
import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.exception.ConnectionException;
import com.ydcqy.ymq.pool.rabbitmq.PooledConnectionFactory;
import com.ydcqy.ymq.pool.rabbitmq.RabbitMqPoolConfig;


/**
 * @author xiaoyu
 */
public class RabbitMqConnectionFactory implements ConnectionFactory {
    private PooledConnectionFactory               pooledCf;
    private com.rabbitmq.client.ConnectionFactory cf;
    private RabbitMqConfiguration                 cfg;

    public RabbitMqConnectionFactory(Configuration configuration) {
        cfg = (RabbitMqConfiguration) configuration;
        init();
    }

    private void init() {
        if (null == cf) {
            cf = new com.rabbitmq.client.ConnectionFactory();
        }
        Object value;
        if ((value = cfg.getHost()) != null) cf.setHost(String.valueOf(value));
        if ((value = cfg.getPort()) != null) cf.setPort((Integer) value);
        if ((value = cfg.getUsername()) != null) cf.setUsername(String.valueOf(value));
        if ((value = cfg.getPassword()) != null) cf.setPassword(String.valueOf(value));
        initPool();
    }

    private void initPool() {
        if (null == pooledCf && null != cfg.getProducerPool()) {
            RabbitMqPoolConfig poolConfig = new RabbitMqPoolConfig();
            Object value;
            if ((value = cfg.getProducerPool().getMaxTotal()) != null) poolConfig.setMaxTotal((Integer) value);
            if ((value = cfg.getProducerPool().getMaxIdle()) != null) poolConfig.setMaxIdle((Integer) value);
            if ((value = cfg.getProducerPool().getMinIdle()) != null) poolConfig.setMinIdle((Integer) value);
            if ((value = cfg.getProducerPool().getMaxWaitMillis()) != null)
                poolConfig.setMaxWaitMillis((Integer) value);
            if ((value = cfg.getProducerPool().getMinEvictableIdleTimeMillis()) != null)
                poolConfig.setMinEvictableIdleTimeMillis((Integer) value);
            if ((value = cfg.getProducerPool().getTimeBetweenEvictionRunsMillis()) != null)
                poolConfig.setMinIdle((Integer) value);
            if ((value = cfg.getProducerPool().getTestOnBorrow()) != null) poolConfig.setTestOnBorrow((Boolean) value);
            if ((value = cfg.getProducerPool().getTestOnReturn()) != null) poolConfig.setTestOnReturn((Boolean) value);
            if ((value = cfg.getProducerPool().getTestWhileIdle()) != null)
                poolConfig.setTestWhileIdle((Boolean) value);
            pooledCf = new PooledConnectionFactory(poolConfig, cf);
        }
    }

    @Override
    public Connection getConnection() throws ConnectionException {
        return getConnection(cfg.getProducerPool() != null);
    }

    @Override
    public Connection getConnection(boolean isPooledConn) throws ConnectionException {
        if (isPooledConn) {
            if (pooledCf == null) {
                throw new ConnectionException("Connection pools are not configured");
            }
            try {
                return new RabbitMqConnection(pooledCf.newConnection());
            } catch (Exception e) {
                throw new ConnectionException(e);
            }
        }
        try {
            return new RabbitMqConnection(cf.newConnection());
        } catch (Exception e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public Configuration getConfiguration() {
        return cfg;
    }

}
