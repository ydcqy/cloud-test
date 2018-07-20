package com.ydcqy.grpc.service.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * @author xiaoyu
 */
@Slf4j
@Component
//@RabbitListener(queues = "talk.o2o.sayhello")
public class RabbitMqSend {

    @PostConstruct
    public void send() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.1.7.22");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("xiaoyu");
        connectionFactory.setPassword("123456");

        Connection conn = connectionFactory.newConnection();
        Channel channel1 = conn.createChannel();

        String exchange = "test.product";
        String queue = "a.b.c";
        channel1.exchangeDeclare(exchange, "topic", true);
        channel1.queueDeclare(queue, true, false, false, null);
        channel1.queueBind(queue, exchange, queue, null);
        System.out.println("设置..");
        for (int i = 0; i < 2; i++) {
//            String str = new Scanner(System.in).nextLine();
            String str = "好咯" + i;
            if (!StringUtils.isEmpty(str)) {
                channel1.basicPublish(exchange, "a.b.c", MessageProperties.MINIMAL_PERSISTENT_BASIC, str.getBytes());
            }
        }
        System.out.println("设置11..");

    }


    public static void main(String[] args) throws Exception {
        new RabbitMqSend().send();
    }
}
