package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.response.Response;

/**
 * @author xiaoyu
 */
public class YrpcResponse implements Response {
    private String requestId;
    private Object result;
    private Throwable throwable;

    @Override
    public String getRequestId() {
        return requestId;
    }

    @Override
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
