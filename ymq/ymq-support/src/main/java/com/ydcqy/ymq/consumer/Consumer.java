package com.ydcqy.ymq.consumer;

import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.message.MessageListener;
import com.ydcqy.ymq.message.Queue;

/**
 * @author xiaoyu
 */
public interface Consumer {
    Consumer bindMessageListener(Queue queue, MessageListener listener);

    Consumer bindMessageListener(Queue queue, Integer consumerConcurrency, MessageListener listener);

    void listen() throws MqException;

}
