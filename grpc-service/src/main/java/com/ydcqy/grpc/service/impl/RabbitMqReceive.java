package com.ydcqy.grpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * @author xiaoyu
 */
@Slf4j

public class RabbitMqReceive {


    public RabbitMqReceive() throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setSharedExecutor(Executors.newFixedThreadPool(1));
        connectionFactory.setHost("10.1.7.22");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("xiaoyu");
        connectionFactory.setPassword("123456");

        Connection conn = connectionFactory.newConnection();
        Channel channel1 = conn.createChannel();

        String exchange = "test.product";
        String queue = "a.b.c";

//        channel1.exchangeDeclare(exchange, "topic", true);
//        channel1.queueDeclare(queue, true, false, false, null);
//        channel1.queueBind(queue, exchange, BindingKey.PRODUCT_STOCK_KEY, null);
        channel1.basicQos(1);
        channel1.basicConsume(queue, false, new DefaultConsumer(null) {
            @Override

            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("11{} {} {}", consumerTag, JSON.toJSONString(envelope), JSON.toJSONString(properties));
                log.info("11poll msg：{}", new String(body));
//                channel1.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Channel channel2 = conn.createChannel();
        channel2.basicConsume(queue, false, new DefaultConsumer(null) {
            @Override

            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("11{} {} {}", consumerTag, JSON.toJSONString(envelope), JSON.toJSONString(properties));
                log.info("11poll msg：{}", new String(body));
//                channel2.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(1000);
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

    public static void main(String[] args) throws Exception {
        new RabbitMqReceive();
    }
}
