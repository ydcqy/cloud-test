package com.ydcqy.ymq.message;

/**
 * @author xiaoyu
 */
public interface Message {
    byte[] getContent();

    Long getDelayMillis();

    Long getPeriodMillis();

    Integer getRepeatNum();
}
