package com.ydcqy.grpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author xiaoyu
 */
@Slf4j
@Component
//@RabbitListener(queues = "talk.o2o.sayhello")
public class RabbitMqReceive {

    @PostConstruct
    public void send() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.1.7.22");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("xiaoyu");
        connectionFactory.setPassword("123456");

        Connection conn = connectionFactory.newConnection();
        Channel channel1 = conn.createChannel();

        String exchange = "test.ydcqy";
        String queue = "grpc.req";
        String routingKey = "grpc.req";
        channel1.exchangeDeclare(exchange, "topic", true);
        channel1.queueDeclare(queue, true, false, false, null);
        channel1.queueBind(queue, exchange, routingKey, null);
        System.out.println("设置..");
        for (int i = 0; i < 10000; i++) {
//            String str = new Scanner(System.in).nextLine();
            String str = "好咯" + i;
            if (!StringUtils.isEmpty(str)) {
                channel1.basicPublish(exchange, routingKey, MessageProperties.MINIMAL_PERSISTENT_BASIC, "".getBytes());
            }
        }
        System.out.println("设置11..");

    }

    public RabbitMqReceive() throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.1.7.22");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("xiaoyu");
        connectionFactory.setPassword("123456");

        Connection conn = connectionFactory.newConnection();
        Channel channel1 = conn.createChannel();

        String exchange = "test.ydcqy";
        String queue = "grpc.req";
        String routingKey = "grpc.req";
        channel1.exchangeDeclare(exchange, "topic", true);
        channel1.queueDeclare(queue, true, false, false, null);
        channel1.queueBind(queue, exchange, routingKey, null);
        channel1.basicQos(1);
        channel1.basicConsume(queue, false, new DefaultConsumer(null) {
            @Override

            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("11{} {} {}", consumerTag, JSON.toJSONString(envelope), JSON.toJSONString(properties));
                log.info("11poll msg：{}", new String(body));
                channel1.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Channel channel2 = conn.createChannel();
        exchange = "test.ydcqy";
        queue = "grpc.req";
        routingKey = "grpc.req";
        channel2.exchangeDeclare(exchange, "topic", true);
        channel2.queueDeclare(queue, true, false, false, null);
        channel2.queueBind(queue, exchange, routingKey, null);
        channel2.basicQos(1);
        channel2.basicConsume(queue, false, new DefaultConsumer(null) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("22{} {} {}", consumerTag, JSON.toJSONString(envelope), JSON.toJSONString(properties));
                log.info("22poll msg：{}", new String(body));
                channel2.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

//        for (int i = 0; i < 100000; i++) {
////            String str = new Scanner(System.in).nextLine();
//            String str = "好咯" + i;
//            if (!StringUtils.isEmpty(str)) {
//        channel1.basicPublish(exchange, routingKey, MessageProperties.MINIMAL_PERSISTENT_BASIC, "".getBytes());
//            }
//        }
//        System.out.println("完成");
        //

    }
}
