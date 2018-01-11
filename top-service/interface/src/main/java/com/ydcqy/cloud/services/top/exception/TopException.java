package com.ydcqy.cloud.services.top.exception;

/**
 * Created by xiaoyu on 2017-12-26.
 */
public class TopException extends Exception {
    private static final long serialVersionUID = 5076189419302945326L;

    public TopException(String message) {
        super(message);
    }

    public TopException(String message, Throwable cause) {
        super(message, cause);
    }
}
