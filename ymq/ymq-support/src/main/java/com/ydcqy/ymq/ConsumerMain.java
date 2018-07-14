package com.ydcqy.ymq;

import com.ydcqy.ymq.connection.ActiveMqConnectionFactory;
import com.ydcqy.ymq.consumer.ActiveMqConsumer;
import com.ydcqy.ymq.consumer.Consumer;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.message.ActiveMqQueue;
import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.message.MessageListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoyu
 */
@Slf4j
public class ConsumerMain {
    public static void main(String[] args) throws MqException {
        Consumer consumer = new ActiveMqConsumer(new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration()));
        consumer.bindMessageListener(new ActiveMqQueue("com.test", ActiveMqQueue.Type.QUEUE),
                new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        log.info(String.valueOf(message.getDecodeObject()) + "," + message.getDecodeObject().getClass());
//                LockSupport.park();
                    }
                }).listen();
    }
}
