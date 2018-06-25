package com.ydcqy.ycache.exception;

/**
 * @author xiaoyu
 */
public class CacheConfigException extends RuntimeException {
    private static final String ERROR_MSG = "Cache config error";

    public CacheConfigException() {
        this(ERROR_MSG);
    }

    public CacheConfigException(String message) {
        super(ERROR_MSG + ":" + message);
    }

    public CacheConfigException(String message, Throwable cause) {
        super(ERROR_MSG + ":" + message, cause);
    }

}
