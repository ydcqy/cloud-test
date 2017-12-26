package com.ydcqy.cloud.services.talk.exception;

/**
 * Created by xiaoyu on 2017-12-26.
 */
public class TalkException extends Exception {
    private static final long serialVersionUID = 5076189419302945326L;

    public TalkException(String message) {
        super(message);
    }

    public TalkException(String message, Throwable cause) {
        super(message, cause);
    }
}
