package com.ydcqy.grpc;

import com.ydcqy.grpc.rpc.HelloReply;
import com.ydcqy.grpc.rpc.HelloRequest;
import com.ydcqy.grpc.rpc.HelloWorldServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * @author xiaoyu
 */
@Slf4j
public class GrpcClientMain {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(1);

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("127.0.0.1", 18100).usePlaintext().build();
        HelloWorldServiceGrpc.HelloWorldServiceStub stub = HelloWorldServiceGrpc.newStub(managedChannel);
//       UserServiceGrpc.newStub(managedChannel);
        StreamObserver<HelloReply> responseObserver = new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply helloReply) {
                log.info("receive msg 【{}】", helloReply.getMessage());

            }

            @Override
            public void onError(Throwable throwable) {
                log.error("error >>> {}", throwable);
            }

            @Override
            public void onCompleted() {
                log.info("====onCompleted====");

                managedChannel.shutdown();
                cdl.countDown();

            }
        };

        StreamObserver<HelloRequest> client = stub.sayHello(responseObserver);
        new Thread(() -> {
            while (true) {
                String str = new Scanner(System.in).nextLine();
                if ("EOF".equals(str)) {
                    client.onCompleted();
                    break;
                }
                log.info("begin to transport ...");
                client.onNext(HelloRequest.newBuilder().setName(str).build());
                client.onNext(HelloRequest.newBuilder().setName(str).build());
                client.onNext(HelloRequest.newBuilder().setName(str).build());
                client.onNext(HelloRequest.newBuilder().setName(str).build());
                client.onNext(HelloRequest.newBuilder().setName(str).build());
                client.onNext(HelloRequest.newBuilder().setName(str).build());
            }
        }).start();

        cdl.await();

    }
}
