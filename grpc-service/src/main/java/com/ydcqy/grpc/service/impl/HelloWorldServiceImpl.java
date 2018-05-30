package com.ydcqy.grpc.service.impl;

import com.ydcqy.grpc.rpc.HelloReply;
import com.ydcqy.grpc.rpc.HelloRequest;
import com.ydcqy.grpc.rpc.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoyu
 */
@Slf4j
public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    @Override
    public StreamObserver<HelloRequest> sayHello(StreamObserver<HelloReply> responseObserver) {

        return new StreamObserver<HelloRequest>() {
            @Override
            public void onNext(HelloRequest helloRequest) {

                log.info("====onNext====");
                String name = helloRequest.getName();
                log.info("request >>> {}", name);
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("error >>> {}", throwable);
            }

            @Override
            public void onCompleted() {
                log.info("====onCompleted====");
                responseObserver.onNext(HelloReply.newBuilder().setMessage("哈喽,,,").build());
                responseObserver.onCompleted();
            }
        };

    }
}
