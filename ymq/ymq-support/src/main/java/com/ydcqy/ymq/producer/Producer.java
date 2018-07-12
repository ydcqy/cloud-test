package com.ydcqy.ymq.producer;

import com.ydcqy.ymq.connection.ConnectionFactory;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.message.Message;
import com.ydcqy.ymq.message.Queue;

/**
 * @author xiaoyu
 */
public interface Producer {
    void send(Queue queue, Message msg) throws MqException;


}
