package com.ydcqy.ymq.producer;

import com.ydcqy.ymq.connection.ActiveMqConnectionFactory;
import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.message.ActiveMqQueue;
import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.message.Queue;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * @author xiaoyu
 */
public class ActiveMqProducer extends AbstractProducer {
    private Session session;
    private MessageProducer producer;

    public ActiveMqProducer(ConnectionFactory connectionFactory) {
        super(connectionFactory);

    }

    @Override
    public void send(Queue queue, Message msg) throws MqException {
        try {
            ActiveMqQueue activeMqQueue = (ActiveMqQueue) queue;
            if (ActiveMqQueue.Type.QUEUE == activeMqQueue.getType()) {
                producer = session.createProducer(session.createQueue(queue.getQueueName()));
            }
        } catch (JMSException e) {
            throw new MqException(e);
        }
    }

}
