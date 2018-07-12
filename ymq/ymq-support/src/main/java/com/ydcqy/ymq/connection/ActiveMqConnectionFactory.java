package com.ydcqy.ymq.connection;

import com.ydcqy.ymq.configuration.ActiveMqConfiguration;
import com.ydcqy.ymq.configuration.Configuration;
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
        if (null == pooledCf) {
            pooledCf = new PooledConnectionFactory(cf);
            pooledCf.setMaxConnections(1);
        }
    }

    @Override
    public Connection getConnection() throws ConnectionException {
        try {
            ActiveMqConnection connection = new ActiveMqConnection(pooledCf.createConnection());
            return connection;
        } catch (JMSException e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public Configuration getConfiguration() {
        return cfg;
    }

}
