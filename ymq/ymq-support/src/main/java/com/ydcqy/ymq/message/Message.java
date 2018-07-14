package com.ydcqy.ymq.message;

/**
 * @author xiaoyu
 */
public interface Message {
    byte[] getEncodeContent();

    Object getDecodeObject();

    Long getDelayMillis();

    Long getPeriodMillis();

    Integer getRepeatNum();
}
