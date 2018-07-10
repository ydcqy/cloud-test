package com.ydcqy.ymq.producer;

import com.ydcqy.ymq.message.Message;

/**
 * @author xiaoyu
 */
public interface Producer {
    void send(Message msg);
}
