package com.ydcqy.ymq.spring.util;

import com.ydcqy.ymq.AbstractConfigurationFactory;
import com.ydcqy.ymq.activemq.ActiveMqConfigurationFactory;
import com.ydcqy.ymq.activemq.ActiveMqConnectionFactory;
import com.ydcqy.ymq.activemq.ActiveMqProducer;
import com.ydcqy.ymq.configuration.Configuration;
import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.kafka.KafkaConfigurationFactory;
import com.ydcqy.ymq.kafka.KafkaProducer;
import com.ydcqy.ymq.producer.Producer;
import com.ydcqy.ymq.rabbitmq.RabbitMqConfigurationFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqConnectionFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqProducer;
import com.ydcqy.ymq.spring.ConfigBean;
import org.springframework.util.StringUtils;

/**
 * @author xiaoyu
 */
public class ProducerHolder {
    private static volatile Producer producer;

    public static Producer get(ConfigBean configBean) {
        synchronized (ProducerHolder.class) {
            if (producer == null) {
                ConnectionFactory connectionFactory;
                Configuration configuration = configBean.getConfiguration();
                String path = configBean.getPath();
                if (StringUtils.isEmpty(path) && null == configuration) {
                    throw new IllegalArgumentException("Configuration error");
                }
                switch (configBean.getActive()) {
                    case ActiveType.ACTIVEMQ:
                        if (configuration != null) {
                            connectionFactory = new ActiveMqConnectionFactory(configuration);
                        } else {
                            AbstractConfigurationFactory.CONFIG_FILE = path;
                            connectionFactory = new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration());
                        }
                        producer = new ActiveMqProducer(connectionFactory);
                        break;
                    case ActiveType.RABBITMQ:
                        if (configuration != null) {
                            connectionFactory = new RabbitMqConnectionFactory(configuration);
                        } else {
                            AbstractConfigurationFactory.CONFIG_FILE = path;
                            connectionFactory = new RabbitMqConnectionFactory(new RabbitMqConfigurationFactory().getConfiguration());
                        }
                        producer = new RabbitMqProducer(connectionFactory);
                        break;
                    case ActiveType.KAFKA:
                        if (configuration != null) {
                            producer = new KafkaProducer(configuration);
                        } else {
                            AbstractConfigurationFactory.CONFIG_FILE = path;
                            producer = new KafkaProducer(new KafkaConfigurationFactory().getConfiguration());
                        }
                        break;
                }
            }
        }
        return producer;
    }

}
