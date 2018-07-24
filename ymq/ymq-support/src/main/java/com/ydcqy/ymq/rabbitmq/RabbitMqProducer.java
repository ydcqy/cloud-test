package com.ydcqy.ymq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.message.Queue;
import com.ydcqy.ymq.producer.AbstractProducer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author xiaoyu
 */
@Slf4j
public class RabbitMqProducer extends AbstractProducer {
    private final        String          exchangeType     = "topic";
    private static final ConfirmListener CONFIRM_LISTENER = new DefaultConfirmListener();

    public RabbitMqProducer(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    protected void doSend(Queue queue, Message msg) throws MqException {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = (Connection) getConnectionFactory().getConnection().getTargetConnection();
            channel = connection.createChannel();
            RabbitMqQueue rabbitMqQueue = (RabbitMqQueue) queue;
            //初始化交换器和队列
            if (!isQueueInitialized(queue)) {
                channel.exchangeDeclare(rabbitMqQueue.getExchangeName(), exchangeType, true);
                channel.queueDeclare(rabbitMqQueue.getQueueName(), true, false, false, null);
                channel.queueBind(rabbitMqQueue.getQueueName(), rabbitMqQueue.getExchangeName(), rabbitMqQueue.getQueueBindingKey(), null);
            }
            //发送
            channel.confirmSelect();
            channel.basicPublish(rabbitMqQueue.getExchangeName(), rabbitMqQueue.getMessageRoutingKey(), MessageProperties.MINIMAL_PERSISTENT_BASIC, msg.getEncodeContent());
            channel.addConfirmListener(CONFIRM_LISTENER);
        } catch (Exception e) {
            throw new MqException(e);
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                throw new MqException(e);
            }
        }
    }

    @Slf4j
    static class DefaultConfirmListener implements ConfirmListener {
        @Override
        public void handleAck(long deliveryTag, boolean multiple) throws IOException {
        }

        @Override
        public void handleNack(long deliveryTag, boolean multiple) throws IOException {
            log.error("confirm nack：{} {}", deliveryTag, multiple);
        }
    }
}
