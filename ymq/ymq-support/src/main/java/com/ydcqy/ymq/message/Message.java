package com.ydcqy.ymq.message;

/**
 * @author xiaoyu
 */
public interface Message {
    byte[] getBytes();

    byte[] getEncodeContent();

    <T> T getDecodeObject(Class<T> type);

    Long getDelayMillis();
}
