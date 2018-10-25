package com.ydcqy.ynet.protocol;

import com.ydcqy.ynet.request.Request;
import com.ydcqy.ynet.response.Response;

/**
 * @author xiaoyu
 */
public abstract class TransferProtocol<REQ extends Request, RESP extends Response> implements Protocol {

    public abstract void setRequest(REQ request);

    public abstract void setResponse(RESP response);

    public abstract REQ getRequest();

    public abstract RESP getResponse();

}
