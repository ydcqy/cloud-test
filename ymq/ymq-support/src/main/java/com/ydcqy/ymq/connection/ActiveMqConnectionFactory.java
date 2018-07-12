package com.ydcqy.ymq.connection;

import com.ydcqy.ymq.configuration.Configuration;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author xiaoyu
 */
public class ActiveMqConnectionFactory implements ConnectionFactory {
    private ActiveMQConnectionFactory cf;

    public ActiveMqConnectionFactory(Configuration configuration) {
        System.out.println(configuration);
    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
