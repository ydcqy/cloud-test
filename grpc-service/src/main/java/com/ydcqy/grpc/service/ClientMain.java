package com.ydcqy.grpc.service;

import com.ydcqy.grpc.service.grpc.HelloServiceGrpc;
import com.ydcqy.grpc.service.grpc.HelloServiceProtos;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class ClientMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientMain.class);

    public static void main(String[] args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 1111).usePlaintext().build();
//        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
//        HelloServiceProtos.Result result = stub.sayHi1(HelloServiceProtos.Params.newBuilder().setName("张三").setAge(123).build());
//        LOGGER.info("sayHi1: " + result);

        HelloServiceGrpc.HelloServiceStub stub = HelloServiceGrpc.newStub(channel);
//        //sayHi1
//        stub.sayHi1(HelloServiceProtos.Params.newBuilder().setName("张三").setAge(123).build(), new StreamObserver<HelloServiceProtos.Result>() {
//            @Override
//            public void onNext(HelloServiceProtos.Result value) {
//                LOGGER.info("sayHi1 onNext: {}", value);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                LOGGER.error("sayHi1 onError: {}", t);
//            }
//
//            @Override
//            public void onCompleted() {
//                LOGGER.info("sayHi1 onCompleted");
//            }
//        });
//        //sayHi2
//        StreamObserver<HelloServiceProtos.Params> paramsStreamObserver = stub.sayHi2(new StreamObserver<HelloServiceProtos.Result>() {
//            @Override
//            public void onNext(HelloServiceProtos.Result value) {
//                LOGGER.info("sayHi2 onNext: {}", value);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                LOGGER.error("sayHi2 onError: {}", t);
//            }
//
//            @Override
//            public void onCompleted() {
//                LOGGER.info("sayHi2 onCompleted");
//            }
//        });
//        paramsStreamObserver.onNext(HelloServiceProtos.Params.newBuilder().setName("张三").setAge(123).build());
//        paramsStreamObserver.onNext(HelloServiceProtos.Params.newBuilder().setName("张三").setAge(123).build());
//        paramsStreamObserver.onCompleted();
//        //sayHi3
//        stub.sayHi3(HelloServiceProtos.Params.newBuilder().setName("张三").setAge(123).build(), new StreamObserver<HelloServiceProtos.Result>() {
//            @Override
//            public void onNext(HelloServiceProtos.Result value) {
//                LOGGER.info("sayHi3 onNext: {}", value);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                LOGGER.error("sayHi3 onError: {}", t);
//            }
//
//            @Override
//            public void onCompleted() {
//                LOGGER.info("sayHi3 onCompleted");
//            }
//        });
        //sayHi4
        StreamObserver<HelloServiceProtos.Params> paramsStreamObserver4 = stub.sayHi4(new StreamObserver<HelloServiceProtos.Result>() {
            @Override
            public void onNext(HelloServiceProtos.Result value) {
                LOGGER.info("sayHi4 onNext: {}", value);
            }

            @Override
            public void onError(Throwable t) {
                LOGGER.error("sayHi4 onError: {}", t);
            }

            @Override
            public void onCompleted() {
                LOGGER.info("sayHi4 onCompleted");
            }
        });
        paramsStreamObserver4.onNext(HelloServiceProtos.Params.newBuilder().setUsername("张三").setAge(123).build());
        paramsStreamObserver4.onNext(HelloServiceProtos.Params.newBuilder().setUsername("张三").setAge(123).build());

        LockSupport.park();
    }
}
