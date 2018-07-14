package com.ydcqy.ymq.consumer;

import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.message.ActiveMqMessage;
import com.ydcqy.ymq.message.ActiveMqQueue;
import com.ydcqy.ymq.message.MessageListener;
import com.ydcqy.ymq.message.Queue;
import com.ydcqy.ymq.util.UnsafeUtil;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.util.ByteSequence;
import sun.misc.Unsafe;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaoyu
 */
public class ActiveMqConsumer extends AbstractConsumer {
    private javax.jms.Connection connection;
    private Unsafe unsafe = UnsafeUtil.getUnsafe();

    public ActiveMqConsumer(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void listen() throws MqException {
        try {
            if (null == connection) {
                connection = ((javax.jms.Connection) getConnectionFactory().getConnection(false).getTargetConnection());
                connection.start();
            }
            ConcurrentHashMap<Queue, List<MessageListener>> queueListener = getQueueListener();
            Set<Map.Entry<Queue, List<MessageListener>>> entries = queueListener.entrySet();
            for (Map.Entry<Queue, List<MessageListener>> entry : entries) {
                Queue q = entry.getKey();
                ActiveMqQueue activeMqQueue = (ActiveMqQueue) q;
                List<MessageListener> mls = entry.getValue();
                for (final MessageListener ml : mls) {
                    Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);

                    Destination dest = null;
                    switch (activeMqQueue.getType()) {
                        case QUEUE:
                            dest = session.createQueue(q.getQueueName());
                            break;
                        case TOPIC:
                            dest = session.createTopic(q.getQueueName());
                            break;
                    }
                    MessageConsumer consumer = session.createConsumer(dest);
                    consumer.setMessageListener(new javax.jms.MessageListener() {
                        @Override
                        public void onMessage(Message message) {
                            ActiveMQBytesMessage msg = (ActiveMQBytesMessage) message;
                            try {
                                msg.acknowledge();
                                ByteSequence dataIn = (ByteSequence) unsafe.getObject(msg, unsafe.objectFieldOffset(org.apache.activemq.command.Message.class.getDeclaredField("content")));
                                ml.onMessage(new ActiveMqMessage(dataIn.getData()));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            throw new MqException(e);
        }
    }

}
