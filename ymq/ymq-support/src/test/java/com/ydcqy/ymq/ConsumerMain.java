package com.ydcqy.ymq;

import com.ydcqy.ymq.activemq.ActiveMqConfigurationFactory;
import com.ydcqy.ymq.activemq.ActiveMqConnectionFactory;
import com.ydcqy.ymq.activemq.ActiveMqConsumer;
import com.ydcqy.ymq.activemq.ActiveMqQueue;
import com.ydcqy.ymq.consumer.Consumer;
import com.ydcqy.ymq.kafka.KafkaConfigurationFactory;
import com.ydcqy.ymq.kafka.KafkaConsumer;
import com.ydcqy.ymq.kafka.KafkaQueue;
import com.ydcqy.ymq.message.QueueType;
import com.ydcqy.ymq.rabbitmq.RabbitMqConfigurationFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqConnectionFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqConsumer;
import com.ydcqy.ymq.rabbitmq.RabbitMqQueue;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author xiaoyu
 */
@Slf4j
public class ConsumerMain {
    @Test
    public void activemq() throws Exception {
        Consumer consumer = new ActiveMqConsumer(new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration(), false));
        consumer.bindMessageListener(new ActiveMqQueue("x.y.z", QueueType.POINT_TO_POINT), message -> {
            log.info(new String(message.getBytes()));
        });
        consumer.listen();
        new Scanner(System.in).nextLine();
    }

    @Test
    public void rabbitmq() throws Exception {
        Consumer consumer = new RabbitMqConsumer(new RabbitMqConnectionFactory(new RabbitMqConfigurationFactory().getConfiguration(), false));
        consumer.bindMessageListener(new RabbitMqQueue("x.y.z", QueueType.POINT_TO_POINT), message -> {
            log.info(new String(message.getBytes()));
        });
        consumer.listen();
        new Scanner(System.in).nextLine();
    }

    @Test
    public void kafka() throws Exception {
        Consumer consumer = new KafkaConsumer(new KafkaConfigurationFactory().getConfiguration());
        consumer.bindMessageListener(new KafkaQueue("x.y.z", QueueType.POINT_TO_POINT), message -> {
            log.info(new String(message.getBytes()));
        });
        consumer.listen();
        new Scanner(System.in).nextLine();
    }

}
