package com.ydcqy.ynet.request;

import com.ydcqy.ynet.response.Response;

import java.io.Serializable;

/**
 * @author xiaoyu
 */
public interface Request<T extends Response> extends Serializable {
    /**
     * @return
     */
    String getRequestId();

    /**
     * @param requestId
     */
    void setRequestId(String requestId);
}
