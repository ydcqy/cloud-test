package com.ydcqy.ymq;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

/**
 * @author xiaoyu
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover:(tcp://10.1.7.22:61616)?timeout=5000");
        ActiveMQPrefetchPolicy policy = new ActiveMQPrefetchPolicy();

        policy.setQueuePrefetch(1);
        connectionFactory.setPrefetchPolicy(policy);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue("com.test");

        MessageProducer producer = session.createProducer(queue);
        long s = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            long ss = System.currentTimeMillis();
            BytesMessage bytesMessage = session.createBytesMessage();

            producer.send(bytesMessage);
//            session.commit();
            System.out.println(System.currentTimeMillis() - ss);
        }
        System.out.println("时间：" + (System.currentTimeMillis() - s));

    }

}

