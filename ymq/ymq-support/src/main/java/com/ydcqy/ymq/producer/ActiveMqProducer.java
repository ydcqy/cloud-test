package com.ydcqy.ymq.producer;

import com.ydcqy.ymq.ActiveMqConfigurationFactory;
import com.ydcqy.ymq.configuration.ActiveMqConfiguration;
import com.ydcqy.ymq.message.Message;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author xiaoyu
 */
public class ActiveMqProducer extends AbstractProducer {
    private ActiveMqConfiguration configuration;

    public ActiveMqProducer() {
        configuration = (ActiveMqConfiguration) new ActiveMqConfigurationFactory().getConfiguration();
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);

    }

    @Override
    public void send(Message msg) {

    }

    public static void main(String[] args) {
        System.out.println(Thread.interrupted());

    }
}
