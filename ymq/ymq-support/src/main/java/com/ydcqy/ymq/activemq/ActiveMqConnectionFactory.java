package com.ydcqy.ymq.activemq;

import com.ydcqy.ymq.configuration.Configuration;
import com.ydcqy.ymq.connection.Connection;
import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.exception.ConnectionException;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.apache.activemq.pool.PooledConnectionFactory;

import javax.jms.JMSException;

/**
 * @author xiaoyu
 */
public class ActiveMqConnectionFactory implements ConnectionFactory {
    private PooledConnectionFactory pooledCf;
    private ActiveMQConnectionFactory cf;
    private ActiveMqConfiguration cfg;

    public ActiveMqConnectionFactory(Configuration configuration) {
        cfg = (ActiveMqConfiguration) configuration;
        init();
    }

    private void init() {
        if (null == cf) {
            cf = new ActiveMQConnectionFactory();
        }
        Object value;
        if ((value = cfg.getBrokerUrl()) != null) cf.setBrokerURL(String.valueOf(value));
        if ((value = cfg.getUsername()) != null) cf.setUserName(String.valueOf(value));
        if ((value = cfg.getPassword()) != null) cf.setPassword(String.valueOf(value));
        ActiveMQPrefetchPolicy policy = new ActiveMQPrefetchPolicy();
        policy.setQueuePrefetch(1);
        cf.setPrefetchPolicy(policy);

    }

    private void initPool() {
        if (null == pooledCf && null != cfg.getProducerPool()) {
            pooledCf = new PooledConnectionFactory(cf);
            Object value;
            if ((value = cfg.getProducerPool().getMaxConnections()) != null)
                pooledCf.setMaxConnections((Integer) value);
            if ((value = cfg.getProducerPool().getIdleTimeout()) != null) pooledCf.setIdleTimeout((Integer) value);
            if ((value = cfg.getProducerPool().getExpiryTimeout()) != null) pooledCf.setExpiryTimeout((Integer) value);
            if ((value = cfg.getProducerPool().getTimeBetweenExpirationCheckMillis()) != null)
                pooledCf.setTimeBetweenExpirationCheckMillis((Integer) value);
        }
    }

    @Override
    public Connection getConnection() throws ConnectionException {
        return getConnection(cfg.getProducerPool() != null);
    }

    @Override
    public Connection getConnection(boolean isPooledConn) throws ConnectionException {
        if (isPooledConn) {
            initPool();
            if (pooledCf == null) {
                throw new ConnectionException("Connection pools are not configured");
            }
            try {
                return new ActiveMqConnection(pooledCf.createConnection());
            } catch (JMSException e) {
                throw new ConnectionException(e);
            }
        }
        try {
            return new ActiveMqConnection(cf.createConnection());
        } catch (JMSException e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public Configuration getConfiguration() {
        return cfg;
    }

}
