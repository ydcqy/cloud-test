package com.ydcqy.grpc.service.impl;

import com.ydcqy.grpc.rpc.HelloReply;
import com.ydcqy.grpc.rpc.HelloRequest;
import com.ydcqy.grpc.rpc.HelloWorldServiceGrpc;
import com.ydcqy.grpc.support.GrpcService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoyu
 */
@Slf4j
@GrpcService("helloWorldService")
public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        String name = request.getName();
        log.info("receive msg 【{}】", name);
        responseObserver.onNext(HelloReply.newBuilder().setMessage("ack:" + name).build());
        responseObserver.onCompleted();
    }

}
