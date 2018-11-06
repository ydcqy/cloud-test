package com.ydcqy.ynet.rpc.thrift;

import com.ydcqy.ynet.rpc.HelloService;
import com.ydcqy.ynet.rpc.proto.YrpcProtos;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TCompactProtocol;

/**
 * @author xiaoyu
 */
public class Test {
    public static void main(String[] args) throws Exception {


        YrpcRequest request = new YrpcRequest();
        request.setRequest_id("20181105150030");
        request.setInterface_name(HelloService.class.getName());
        request.setMethod_name("sayHiJson");

        TSerializer serializer = new TSerializer(new TCompactProtocol.Factory());
        TDeserializer deserializer = new TDeserializer(new TCompactProtocol.Factory());
        YrpcRequest obj = new YrpcRequest();
        deserializer.deserialize(obj, serializer.serialize(request));
        System.out.println(obj);
        System.out.println(serializer.serialize(request).length);


        YrpcProtos.YrpcRequest r = YrpcProtos.YrpcRequest.newBuilder().setRequestId("20181105150030")
                .setInterfaceName(HelloService.class.getName())
                .setMethodName("sayHiJson").build();
        System.out.println(r.getSerializedSize());

    }
}
