package com.ydcqy.ynet.protocol;

import com.ydcqy.ynet.request.Request;
import com.ydcqy.ynet.response.Response;

/**
 * @author xiaoyu
 */
public abstract class TransferProtocol implements Protocol {

    public abstract void setRequest(Request request);

    public abstract void setResponse(Response response);

    public abstract Request getRequest();

    public abstract Response getResponse();
}
