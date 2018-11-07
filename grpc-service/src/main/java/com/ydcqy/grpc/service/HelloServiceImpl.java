package com.ydcqy.grpc.service;

import com.ydcqy.grpc.service.grpc.HelloServiceGrpc;
import com.ydcqy.grpc.service.grpc.HelloServiceProtos;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaoyu
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public void sayHi1(HelloServiceProtos.Params request, StreamObserver<HelloServiceProtos.Result> responseObserver) {
        LOGGER.info("sayHi1: {}", request);
        responseObserver.onNext(HelloServiceProtos.Result.newBuilder().setValue("hi1").build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<HelloServiceProtos.Params> sayHi2(StreamObserver<HelloServiceProtos.Result> responseObserver) {
        return new StreamObserver<HelloServiceProtos.Params>() {
            @Override
            public void onNext(HelloServiceProtos.Params value) {
                LOGGER.info("sayHi2 onNext: {}", value);
            }

            @Override
            public void onError(Throwable t) {
                LOGGER.error("sayHi2 onError: {}", t);
            }

            @Override
            public void onCompleted() {
                LOGGER.info("sayHi2 onCompleted");
                responseObserver.onNext(HelloServiceProtos.Result.newBuilder().setValue("hi2").build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void sayHi3(HelloServiceProtos.Params request, StreamObserver<HelloServiceProtos.Result> responseObserver) {
        LOGGER.info("sayHi3: {}", request);
        responseObserver.onNext(HelloServiceProtos.Result.newBuilder().setValue("hi3").build());
        responseObserver.onNext(HelloServiceProtos.Result.newBuilder().setValue("hi3").build());
        responseObserver.onNext(HelloServiceProtos.Result.newBuilder().setValue("hi3").build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<HelloServiceProtos.Params> sayHi4(StreamObserver<HelloServiceProtos.Result> responseObserver) {
        System.out.println("哈哈哈");
        return new StreamObserver<HelloServiceProtos.Params>() {
            @Override
            public void onNext(HelloServiceProtos.Params value) {
                LOGGER.info("sayHi4 onNext: {}", value);
                responseObserver.onNext(HelloServiceProtos.Result.newBuilder().setValue("hi4").build());
            }

            @Override
            public void onError(Throwable t) {
                LOGGER.error("sayHi4 onError: {}", t);
            }

            @Override
            public void onCompleted() {
                LOGGER.info("sayHi4 onCompleted");
                responseObserver.onCompleted();
            }
        };
    }

}
