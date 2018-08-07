package com.ydcqy.ymq.spring.util;

import com.ydcqy.ymq.AbstractConfigurationFactory;
import com.ydcqy.ymq.activemq.ActiveMqConfigurationFactory;
import com.ydcqy.ymq.activemq.ActiveMqConnectionFactory;
import com.ydcqy.ymq.activemq.ActiveMqConsumer;
import com.ydcqy.ymq.configuration.Configuration;
import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.consumer.Consumer;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.kafka.KafkaConfigurationFactory;
import com.ydcqy.ymq.kafka.KafkaConsumer;
import com.ydcqy.ymq.producer.Producer;
import com.ydcqy.ymq.rabbitmq.RabbitMqConfigurationFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqConnectionFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqConsumer;
import com.ydcqy.ymq.spring.ConfigBean;
import org.springframework.util.StringUtils;

/**
 * @author xiaoyu
 */
public class ConsumerHolder {
    private static volatile Consumer consumer;
    private static volatile Producer producer;

    public static Consumer get(ConfigBean configBean) {
        synchronized (ConsumerHolder.class) {
            if (consumer == null) {
                ConnectionFactory connectionFactory;
                Configuration configuration = configBean.getConfiguration();
                String path = configBean.getPath();
                if (StringUtils.isEmpty(path) && null == configuration) {
                    throw new IllegalArgumentException("Configuration error");
                }

                switch (configBean.getActive()) {
                    case ActiveType.ACTIVEMQ:
                        if (configuration != null) {
                            connectionFactory = new ActiveMqConnectionFactory(configuration, false);
                        } else {
                            AbstractConfigurationFactory.CONFIG_FILE = path;
                            connectionFactory = new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration(), false);
                        }
                        consumer = new ActiveMqConsumer(connectionFactory);
                        break;
                    case ActiveType.RABBITMQ:
                        if (configuration != null) {
                            connectionFactory = new RabbitMqConnectionFactory(configuration, false);
                        } else {
                            AbstractConfigurationFactory.CONFIG_FILE = path;
                            connectionFactory = new RabbitMqConnectionFactory(new RabbitMqConfigurationFactory().getConfiguration(), false);
                        }
                        consumer = new RabbitMqConsumer(connectionFactory);
                        break;
                    case ActiveType.KAFKA:
                        if (configuration != null) {
                            consumer = new KafkaConsumer(configuration);
                        } else {
                            AbstractConfigurationFactory.CONFIG_FILE = path;
                            consumer = new KafkaConsumer(new KafkaConfigurationFactory().getConfiguration());
                        }
                        break;
                }
            }
        }
        return consumer;
    }

    public static void listen() throws MqException {
        if (consumer != null) {
            consumer.listen();
        }
    }
}
