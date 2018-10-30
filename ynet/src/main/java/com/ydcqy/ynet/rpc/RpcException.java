package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.exception.RemoteException;

/**
 * @author xiaoyu
 */
public class RpcException extends RemoteException {
    public RpcException() {
    }

    public RpcException(String message) {
        super(message);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(Throwable cause) {
        super(cause);
    }
}
