package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.exception.RemoteException;

/**
 * @author xiaoyu
 */
public class RpcException extends RemoteException {
    private String requestId;

    public RpcException(String requestId, String message) {
        super(message);
        this.requestId = requestId;
    }

    public RpcException(String requestId, String message, Throwable cause) {
        super(message, cause);
        this.requestId = requestId;

    }

    public String getRequestId() {
        return requestId;
    }
}
