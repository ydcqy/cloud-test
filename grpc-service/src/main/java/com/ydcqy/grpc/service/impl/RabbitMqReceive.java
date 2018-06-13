package com.ydcqy.grpc.service.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author xiaoyu
 */
@Slf4j
@Component
//@RabbitListener(queues = "talk.o2o.sayhello")
public class RabbitMqReceive {
    public RabbitMqReceive() throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.1.7.22");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("xiaoyu");
        connectionFactory.setPassword("123456");

        Connection conn = connectionFactory.newConnection();
        Channel channel = conn.createChannel();

        String exchange = "test.ydcqy";
        String queue = "grpc.req1";
        String routingKey = "grpc.req";
        channel.exchangeDeclare(exchange, "topic", true);
        channel.queueDeclare(queue, true, false, false, null);
        channel.queueBind(queue, exchange, routingKey, null);
//        channel.basicQos(2);
//        DefaultConsumer consumer = new DefaultConsumer(channel);
//        channel.basicConsume(queue, false, new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                log.info("{} {} {}", consumerTag, JSON.toJSONString(envelope), JSON.toJSONString(properties));
//                log.info("poll msg：{}", new String(body));
////                channel.basicAck(envelope.getDeliveryTag(), false);
//            }
//        });
        channel.basicQos(1,false);
        for (int i = 0; i < 100000; i++) {
//            String str = new Scanner(System.in).nextLine();
            String str = "好咯" + i;
            if (!StringUtils.isEmpty(str)) {
                channel.basicPublish(exchange, routingKey, null, str.getBytes());
            }
        }
        System.out.println("完成");
        //

    }
}
