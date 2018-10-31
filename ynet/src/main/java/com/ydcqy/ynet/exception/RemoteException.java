package com.ydcqy.ynet.exception;

/**
 * @author xiaoyu
 */
public class RemoteException extends YnetException {
    private static final long serialVersionUID = 867807744701792205L;

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
