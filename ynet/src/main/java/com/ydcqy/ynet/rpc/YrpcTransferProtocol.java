package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.protocol.TransferProtocol;
import com.ydcqy.ynet.request.Request;
import com.ydcqy.ynet.response.Response;

/**
 * @author xiaoyu
 */
public class YrpcTransferProtocol extends TransferProtocol {
    @Override
    public String getProtocelName() {
        return "YRPC";
    }

    @Override
    public String getProtocelVersion() {
        return "1.0";
    }


    @Override
    public void setRequest(Request request) {

    }

    @Override
    public void setResponse(Response response) {

    }

    @Override
    public Request getRequest() {
        return null;
    }

    @Override
    public Response getResponse() {
        return null;
    }
}
