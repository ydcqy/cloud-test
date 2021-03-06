package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.response.Response;

/**
 * @author xiaoyu
 */
public class YrpcResponse implements Response {
    private String requestId;
    private Object result;
    private String errMsg;

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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
