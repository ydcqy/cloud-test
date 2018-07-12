package com.ydcqy.ymq.producer;

import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.message.Queue;

/**
 * @author xiaoyu
 */
public class ActiveMqProducer extends AbstractProducer {
    public ActiveMqProducer() {
    }

    public ActiveMqProducer(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void send(Queue queue, Message msg) {
    }

}
