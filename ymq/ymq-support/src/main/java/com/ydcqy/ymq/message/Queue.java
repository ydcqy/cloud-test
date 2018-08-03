package com.ydcqy.ymq.message;

/**
 * @author xiaoyu
 */
public interface Queue {
    String getQueueName();

    Type getType();

    enum Type {
        POINT_TO_POINT,
        PUBLISH_SUBSCRIBE
    }
}
