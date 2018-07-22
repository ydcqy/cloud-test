package com.ydcqy.ymq.activemq;

import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.consumer.AbstractConsumer;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.message.MessageExecutor;
import com.ydcqy.ymq.message.MessageListener;
import com.ydcqy.ymq.message.Queue;
import com.ydcqy.ymq.util.UnsafeUtils;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.util.ByteSequence;
import sun.misc.Unsafe;

import javax.jms.Connection;
import javax.jms.Destination;
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
    private Connection connection;
    private Unsafe unsafe = UnsafeUtils.getUnsafe();

    public ActiveMqConsumer(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void listen() throws MqException {
        try {
            if (null == connection) {
                connection = (Connection) getConnectionFactory().getConnection(false).getTargetConnection();
                connection.start();
            }
            ConcurrentHashMap<Queue, List<MessageListener>> queueListener = getQueueListener();
            Set<Map.Entry<Queue, List<MessageListener>>> entries = queueListener.entrySet();
            for (Map.Entry<Queue, List<MessageListener>> entry : entries) {
                Queue q = entry.getKey();
                ActiveMqQueue activeMqQueue = (ActiveMqQueue) q;
                List<MessageListener> mls = entry.getValue();
                MessageExecutor executor = new MessageExecutor("activemq", mls.size());
                for (final MessageListener ml : mls) {
                    if (null == executor.getMessageListener()) {
                        executor.setMessageListener(ml);
                    }
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
                    consumer.setMessageListener(message -> {
                        ActiveMQBytesMessage msg = (ActiveMQBytesMessage) message;
                        try {
                            msg.acknowledge();
                            ByteSequence dataIn = (ByteSequence) unsafe.getObject(msg, unsafe.objectFieldOffset(org.apache.activemq.command.Message.class.getDeclaredField("content")));
                            executor.onMessage(new ActiveMqMessage(dataIn.getData()));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        } catch (Exception e) {
            throw new MqException(e);
        }
    }

}
