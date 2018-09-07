package com.ydcqy.ymq;

import com.ydcqy.ymq.activemq.ActiveMqQueue;
import com.ydcqy.ymq.consumer.Consumer;
import com.ydcqy.ymq.message.QueueType;
import com.ydcqy.ymq.rabbitmq.RabbitMqConfigurationFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqConnectionFactory;
import com.ydcqy.ymq.rabbitmq.RabbitMqConsumer;
import com.ydcqy.ymq.rabbitmq.RabbitMqQueue;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

/**
 * @author xiaoyu
 */
@Slf4j
public class ConsumerMain implements Serializable {
    public static void main(String[] args) throws Exception {
//        Consumer consumer = new ActiveMqConsumer(new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration(), false));
        Consumer consumer = new RabbitMqConsumer(new RabbitMqConnectionFactory(new RabbitMqConfigurationFactory().getConfiguration(), false));
//        Consumer consumer = new KafkaConsumer(new KafkaConfigurationFactory().getConfiguration());
        CountDownLatch countDownLatch = new CountDownLatch(Integer.MAX_VALUE);
        long ss = System.currentTimeMillis();
        consumer.bindMessageListener(
//                new ActiveMqQueue("x.y.z", QueueType.POINT_TO_POINT),
                new RabbitMqQueue("wechat",QueueType.POINT_TO_POINT),
//                new KafkaQueue("x.y.z11113", QueueType.POINT_TO_POINT),
                message -> {
                    log.info(String.valueOf(message.getDecodeObject(String.class)));
                    countDownLatch.countDown();
                }
        )
                .listen();
//        countDownLatch.await();
        System.out.println("耗时：" + (System.currentTimeMillis() - ss));
//        System.exit(0);

    }
}
