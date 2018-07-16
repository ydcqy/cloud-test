package com.ydcqy.ymq;

import com.ydcqy.ymq.activemq.ActiveMqConfigurationFactory;
import com.ydcqy.ymq.activemq.ActiveMqConnectionFactory;
import com.ydcqy.ymq.activemq.ActiveMqConsumer;
import com.ydcqy.ymq.activemq.ActiveMqQueue;
import com.ydcqy.ymq.consumer.Consumer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author xiaoyu
 */
@Slf4j
public class ConsumerMain {
    public static void main(String[] args) throws Exception {
        Consumer consumer = new ActiveMqConsumer(new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration()));
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        long ss = System.currentTimeMillis();
        consumer.bindMessageListener(new ActiveMqQueue("com.test", ActiveMqQueue.Type.QUEUE), 5,
                message -> {
                    log.info(String.valueOf(message.getDecodeObject()) + "," + message.getDecodeObject().getClass());
                    countDownLatch.countDown();
                }
        ).listen();
        countDownLatch.await();
        System.out.println("耗时：" + (System.currentTimeMillis() - ss));
        System.exit(0);
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
