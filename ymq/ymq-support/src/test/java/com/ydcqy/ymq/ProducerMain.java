package com.ydcqy.ymq;

import com.ydcqy.ymq.activemq.ActiveMqConfigurationFactory;
import com.ydcqy.ymq.activemq.ActiveMqConnectionFactory;
import com.ydcqy.ymq.activemq.ActiveMqMessage;
import com.ydcqy.ymq.activemq.ActiveMqProducer;
import com.ydcqy.ymq.activemq.ActiveMqQueue;
import com.ydcqy.ymq.kafka.KafkaConfigurationFactory;
import com.ydcqy.ymq.kafka.KafkaProducer;
import com.ydcqy.ymq.kafka.KafkaQueue;
import com.ydcqy.ymq.message.QueueType;
import com.ydcqy.ymq.producer.Producer;
import com.ydcqy.ymq.rabbitmq.RabbitMqConfigurationFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqConnectionFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqMessage;
import com.ydcqy.ymq.rabbitmq.RabbitMqProducer;
import com.ydcqy.ymq.rabbitmq.RabbitMqQueue;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author xiaoyu
 */
@Slf4j
public class ProducerMain {
    @Test
    public void activemq() throws Exception {
        Producer producer = new ActiveMqProducer(new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration(), false));
        producer.send(new ActiveMqQueue("x.y.z", QueueType.POINT_TO_POINT), new ActiveMqMessage("哈哈".getBytes()));
    }

    @Test
    public void rabbitmq() throws Exception {
        Producer producer = new RabbitMqProducer(new RabbitMqConnectionFactory(new RabbitMqConfigurationFactory().getConfiguration(), false));
        producer.send(new RabbitMqQueue("x.y.z", QueueType.POINT_TO_POINT), new RabbitMqMessage("哈喽大圣归来", 1000L));
    }

    @Test
    public void kafka() throws Exception {
        Producer producer = new KafkaProducer(new KafkaConfigurationFactory().getConfiguration());
        producer.send(new KafkaQueue("x.y.z", QueueType.POINT_TO_POINT), new RabbitMqMessage("哈喽大圣归来"));
    }

}
