package com.ydcqy.ynet.rpc.thrift;

import com.ydcqy.ynet.rpc.HelloService;
import com.ydcqy.ynet.rpc.proto.YrpcProtos;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.apache.thrift.transport.TByteBuffer;
import org.apache.thrift.transport.TMemoryBuffer;

import java.nio.ByteBuffer;

/**
 * @author xiaoyu
 */
public class Test {
    public static void main(String[] args) throws Exception {
        YrpcRequest request = new YrpcRequest();
        request.setRequest_id("20181105150030");
        request.setInterface_name(HelloService.class.getName());
        request.setMethod_name("sayHiJson");

//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        TMemoryBuffer buffer1 = new TMemoryBuffer(1);
        TByteBuffer buffer2 = new TByteBuffer(ByteBuffer.allocate(1000));
//        request.write(new TBinaryProtocol(buffer2));
        request.write(new TSimpleJSONProtocol(buffer2));

        System.out.println(buffer2.getByteBuffer());

        YrpcProtos.YrpcRequest r = YrpcProtos.YrpcRequest.newBuilder().setRequestId("20181105150030")
                .setInterfaceName(HelloService.class.getName())
                .setMethodName("sayHiJson").build();
        System.out.println(r.getSerializedSize());

    }
}
