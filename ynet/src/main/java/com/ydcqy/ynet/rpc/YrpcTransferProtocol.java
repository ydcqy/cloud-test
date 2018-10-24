package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.protocol.TransferProtocol;

/**
 * @author xiaoyu
 */
public class YrpcTransferProtocol extends TransferProtocol<YrpcRequest, YrpcResponse> {
    @Override
    public String getProtocelName() {
        return "YRPC";
    }

    @Override
    public String getProtocelVersion() {
        return "1.0";
    }


    @Override
    public void setRequest(YrpcRequest request) {

    }

    @Override
    public void setResponse(YrpcResponse response) {

    }

    @Override
    public YrpcRequest getRequest() {
        return null;
    }

    @Override
    public YrpcResponse getResponse() {
        return null;
    }
}
