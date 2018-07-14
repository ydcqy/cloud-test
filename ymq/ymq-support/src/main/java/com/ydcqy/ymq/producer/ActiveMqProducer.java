package com.ydcqy.ymq.producer;

import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.message.ActiveMqQueue;
import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.message.Queue;
import org.apache.activemq.ScheduledMessage;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * @author xiaoyu
 */
public class ActiveMqProducer extends AbstractProducer {

    public ActiveMqProducer(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void send(Queue queue, Message msg) throws MqException {
        javax.jms.Connection connection = null;
        try {
            connection = ((javax.jms.Connection) getConnectionFactory().getConnection().getTargetConnection());
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.SESSION_TRANSACTED);
            ActiveMqQueue activeMqQueue = (ActiveMqQueue) queue;
            Destination dest = null;
            switch (activeMqQueue.getType()) {
                case QUEUE:
                    dest = session.createQueue(queue.getQueueName());
                    break;
                case TOPIC:
                    dest = session.createTopic(queue.getQueueName());
                    break;
            }
            BytesMessage message = session.createBytesMessage();
            if (null != msg.getDelayMillis())
                message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, msg.getDelayMillis());
            if (null != msg.getPeriodMillis())
                message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, msg.getPeriodMillis());
            if (null != msg.getRepeatNum())
                message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, msg.getRepeatNum());
            message.writeBytes(msg.getEncodeContent());
            MessageProducer producer = session.createProducer(dest);
            producer.send(message);
            session.commit();
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

}
