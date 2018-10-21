package com.ydcqy.ynet.rpc;

import com.ydcqy.ynet.request.Request;

import java.util.List;

/**
 * @author xiaoyu
 */
public class YrpcRequest implements Request<YrpcResponse> {
    private String requestId;
    private String group;
    private String version;
    private String interfaceName;
    private String methodName;
    private List<Object> paramsList;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<Object> getParamsList() {
        return paramsList;
    }

    public void setParamsList(List<Object> paramsList) {
        this.paramsList = paramsList;
    }

    @Override
    public Class<YrpcResponse> getResponseClass() {
        return YrpcResponse.class;
    }
}
