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
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

/**
 * @author xiaoyu
 */
@Slf4j
public class ConsumerMain implements Serializable {
    public static void main(String[] args) throws Exception {
        Consumer consumer = new ActiveMqConsumer(new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration(), false));
//        Consumer consumer = new RabbitMqConsumer(new RabbitMqConnectionFactory(new RabbitMqConfigurationFactory().getConfiguration(), false));
//        Consumer consumer = new KafkaConsumer(new KafkaConfigurationFactory().getConfiguration());
//        CountDownLatch countDownLatch = new CountDownLatch(Integer.MAX_VALUE);
//        long ss = System.currentTimeMillis();
//        consumer.bindMessageListener(
//                new ActiveMqQueue("x.y.z", QueueType.POINT_TO_POINT),
////                new RabbitMqQueue("x.y.z"),
////                new KafkaQueue("x.y.z11113", QueueType.POINT_TO_POINT),
//                message -> {
//                    log.info(String.valueOf(message.getDecodeObject(String.class)));
//                    countDownLatch.countDown();
//                }
//        )
//                .listen();
////        countDownLatch.await();
//        System.out.println("耗时：" + (System.currentTimeMillis() - ss));
//        System.exit(0);
//        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover:(tcp://10.1.7.22:61616)?timeout=5000");
//        ActiveMQPrefetchPolicy policy = new ActiveMQPrefetchPolicy();
//
//                policy.setQueuePrefetch(1);
//        connectionFactory. setPrefetchPolicy(policy);
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
//        Queue queue = session.createQueue("com.test");
//        MessageConsumer consumer = session.createConsumer(queue);
//        consumer.setMessageListener(new javax.jms.MessageListener() {
//            @Override
//            public void onMessage(javax.jms.Message message) {
//                ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
//                System.out.println(threadGroup.activeGroupCount());
//                log.info(JSON.toJSONString(message));
//
//            }
//        });


    }
}
