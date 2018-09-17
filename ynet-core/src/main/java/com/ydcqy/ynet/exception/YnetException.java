package com.ydcqy.ynet.exception;

/**
 * @author xiaoyu
 */
public class YnetException extends Exception {
    public YnetException() {
    }

    public YnetException(String message) {
        super(message);
    }

    public YnetException(String message, Throwable cause) {
        super(message, cause);
    }

    public YnetException(Throwable cause) {
        super(cause);
    }

}
