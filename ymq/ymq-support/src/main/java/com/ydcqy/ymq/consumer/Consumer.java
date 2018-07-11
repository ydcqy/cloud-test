package com.ydcqy.ymq.consumer;

import com.ydcqy.ymq.message.MessageListener;

/**
 * @author xiaoyu
 */
public interface Consumer {
    void setMessageListener(MessageListener listener);
}
