package com.ydcqy.grpc;

import com.ydcqy.grpc.rpc.HelloReply;
import com.ydcqy.grpc.rpc.HelloRequest;
import com.ydcqy.grpc.rpc.HelloWorldServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import sun.security.util.Debug;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaoyu
 */
@Slf4j
public class GrpcClientMain {
    public static void main(String[] args) throws InterruptedException {
        Debug.println("", "");
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("127.0.0.1", 18100).usePlaintext().build();
        HelloWorldServiceGrpc.HelloWorldServiceStub stub = HelloWorldServiceGrpc.newStub(managedChannel);
        StreamObserver<HelloReply> responseObserver = new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply helloReply) {
                log.info("client:::next");
                String message = helloReply.getMessage();
                log.info(message);

            }

            @Override
            public void onError(Throwable throwable) {
                log.error("error >>> {}", throwable);
            }

            @Override
            public void onCompleted() {
                log.info("====onCompleted====");
                managedChannel.shutdown();
            }
        };

        StreamObserver<HelloRequest> client = stub.sayHello(responseObserver);
        client.onNext(HelloRequest.newBuilder().setName("张三..").build());
        client.onCompleted();
        managedChannel.awaitTermination(30000, TimeUnit.MILLISECONDS);
    }
}
