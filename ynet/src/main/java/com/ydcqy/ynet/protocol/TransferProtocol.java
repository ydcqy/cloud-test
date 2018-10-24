package com.ydcqy.ynet.protocol;

import com.ydcqy.ynet.request.Request;
import com.ydcqy.ynet.response.Response;
import com.ydcqy.ynet.util.SerializationUtils;

/**
 * @author xiaoyu
 */
public abstract class TransferProtocol<REQ extends Request, RESP extends Response> implements Protocol {

    public abstract void setRequest(REQ request);

    public abstract void setResponse(RESP response);

    public abstract REQ getRequest();

    public abstract RESP getResponse();

    public static void main(String[] args) throws Exception {
        Thread.sleep(1000);
        for (; ; ) {
            long ss = System.currentTimeMillis();
            YrpcProtos.YrpcRequest request = YrpcProtos.YrpcRequest.newBuilder().setRequestId("哈哈哈").build();
            System.out.println(request.toByteArray().length);
            System.out.println("耗时:" + (System.currentTimeMillis() - ss));
            ss = System.currentTimeMillis();
            YrpcProtos.YrpcRequest parse = YrpcProtos.YrpcRequest.parseFrom(request.toByteArray());
            System.out.println(SerializationUtils.serialize("哈哈哈").length);
            System.out.println("耗时:" + (System.currentTimeMillis() - ss));

        }

    }
}
