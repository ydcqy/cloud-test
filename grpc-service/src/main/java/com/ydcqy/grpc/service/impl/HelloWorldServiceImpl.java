package com.ydcqy.grpc.service.impl;

import com.ydcqy.grpc.exception.GrpcException;
import com.ydcqy.grpc.rpc.HelloReply;
import com.ydcqy.grpc.rpc.HelloRequest;
import com.ydcqy.grpc.rpc.HelloWorldServiceGrpc;
import com.ydcqy.grpc.service.HelloWorldService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaoyu
 */
@Slf4j
public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase implements HelloWorldService{
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        String name = request.getName();
        log.info("receive msg 【{}】", name);
        responseObserver.onNext(HelloReply.newBuilder().setMessage("ack:" + name).build());
        responseObserver.onCompleted();
    }

    @Override
    public String sayHello(String name) throws GrpcException {
        return null;
    }

//    @Override
//    public StreamObserver<HelloRequest> sayHello(StreamObserver<HelloReply> responseObserver) {
//        log.info("====sayHello====");
//        try {
//            Thread.sleep(10000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new StreamObserver<HelloRequest>() {
//            @Override
//            public void onNext(HelloRequest helloRequest) {
//                String name = helloRequest.getName();
//                log.info("receive msg 【{}】", name);
//                try {
//                    Thread.sleep(10000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                responseObserver.onNext(HelloReply.newBuilder().setMessage("ack:" + name).build());
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                log.error("error >>> {}", throwable);
//            }
//
//            @Override
//            public void onCompleted() {
//                log.info("====onCompleted====");
//                responseObserver.onCompleted();
//            }
//        };
//
//    }
}
