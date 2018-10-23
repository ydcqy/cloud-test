package com.ydcqy.ynet.exception;

/**
 * @author xiaoyu
 */
public class RemoteException extends YnetException {
    public RemoteException() {
    }

    public RemoteException(String message) {
        super(message);
    }

    public RemoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteException(Throwable cause) {
        super(cause);
    }

}
