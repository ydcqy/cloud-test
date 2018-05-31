package com.ydcqy.grpc.service.impl;

import com.ydcqy.grpc.rpc.HelloReply;
import com.ydcqy.grpc.rpc.HelloRequest;
import com.ydcqy.grpc.rpc.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoyu
 */
@Slf4j
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public StreamObserver<HelloRequest> get(StreamObserver<HelloReply> responseObserver) {
        log.info("====get====");
        return new StreamObserver<HelloRequest>() {
            @Override
            public void onNext(HelloRequest helloRequest) {
                String name = helloRequest.getName();
                log.info("receive msg 【{}】", name);
                responseObserver.onNext(HelloReply.newBuilder().setMessage("ack:" + name).build());
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("error >>> {}", throwable);
            }

            @Override
            public void onCompleted() {
                log.info("====onCompleted====");
                responseObserver.onCompleted();
            }
        };

    }
}
