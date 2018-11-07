//package com.ydcqy.grpc;
//
//import com.google.common.util.concurrent.ThreadFactoryBuilder;
//import com.ydcqy.grpc.rpc.HelloReply;
//import com.ydcqy.grpc.rpc.HelloRequest;
//import com.ydcqy.grpc.rpc.HelloWorldServiceGrpc;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Scanner;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.CyclicBarrier;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author xiaoyu
// */
//@Slf4j
//public class GrpcClientMain {
//
//
//    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch cdl = new CountDownLatch(1);
//
//        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("127.0.0.1", 8111).usePlaintext().build();
//
//        HelloWorldServiceGrpc.HelloWorldServiceBlockingStub stub = HelloWorldServiceGrpc.newBlockingStub(managedChannel);
//        ExecutorService executorService =
//                new ThreadPoolExecutor(1000,
//                        1000, 0, TimeUnit.SECONDS,
//                        new LinkedBlockingQueue(), new ThreadFactoryBuilder().setNameFormat("grpc-client-%d").build());
//        CountDownLatch countDownLatch = new CountDownLatch(1000);
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(1000);
//        for (int i = 0; i < 1000; i++) {
//            Thread.sleep(1);
//            executorService.execute(() -> {
//                HelloReply helloReply = stub.sayHello(HelloRequest.newBuilder().setName("你好,世界!").build());
//                log.info("receive msg 【{}】", helloReply.getMessage());
//            });
//        }
//
////        new Thread(){
////            @Override
////            public void run() {
////                HelloReply helloReply = stub.sayHello(HelloRequest.newBuilder().setName("abc" + "R").build());
////                log.info("receive msg 【{}】", helloReply.getMessage());
////            }
////        }.start();
////        new Thread(){
////            @Override
////            public void run() {
////                HelloReply helloReply = stub.sayHello(HelloRequest.newBuilder().setName("abc" + "R").build());
////                log.info("receive msg 【{}】", helloReply.getMessage());
////            }
////        }.start();
////        new Thread(){
////            @Override
////            public void run() {
////                HelloReply helloReply = stub.sayHello(HelloRequest.newBuilder().setName("abc" + "R").build());
////                log.info("receive msg 【{}】", helloReply.getMessage());
////            }
////        }.start();
////        new Thread(){
////            @Override
////            public void run() {
////                HelloReply helloReply = stub.sayHello(HelloRequest.newBuilder().setName("abc" + "R").build());
////                log.info("receive msg 【{}】", helloReply.getMessage());
////            }
////        }.start();
////        StreamObserver<HelloReply> responseObserver = new StreamObserver<HelloReply>() {
////            @Override
////            public void onNext(HelloReply helloReply) {
////                log.info("receive msg 【{}】", helloReply.getMessage());
//////                try {
//////                    Thread.sleep(5000);
//////                } catch (InterruptedException e) {
//////                    e.printStackTrace();
//////                }
////            }
////
////            @Override
////            public void onError(Throwable throwable) {
////                log.error("error >>> {}", throwable);
////            }
////
////            @Override
////            public void onCompleted() {
////                log.info("====onCompleted====");
////
////                managedChannel.shutdown();
////                cdl.countDown();
////
////            }
////        };
////
////        StreamObserver<HelloRequest> client = HelloWorldServiceGrpc.newStub(managedChannel).sayHello(responseObserver);
////        HelloWorldServiceGrpc.HelloWorldServiceStub stub = HelloWorldServiceGrpc.newStub(managedChannel);
////        for (int i = 0; i < 100; i++) {
////            HelloWorldServiceGrpc.newStub(ManagedChannelBuilder.forAddress("127.0.0.1", 18100).usePlaintext().build()).sayHello(responseObserver);
////        }
////        new Thread(() -> {
////            while (true) {
////                String str = new Scanner(System.in).nextLine();
////                if ("EOF".equals(str)) {
////                    HelloWorldServiceGrpc.newStub(managedChannel).sayHello(responseObserver).onCompleted();
////                    break;
////                }
////                log.info("begin to transport ...");
////
////                new Thread(() -> {
////                    new Thread(() -> {
////                        log.info("begin to transport ...");
////                        stub.sayHello(responseObserver).onNext(HelloRequest.newBuilder().setName("abc" + "R").build());
////
////                    }).start();
////                    new Thread(() -> {
////                        log.info("begin to transport ...");
////                        stub.sayHello(responseObserver).onNext(HelloRequest.newBuilder().setName("abc" + "R").build());
////
////                    }).start();
////
////                }).start();
////
////            }
////        }).start();
//
//        new Scanner(System.in).nextLine();
//        managedChannel.shutdown();
//
//    }
//}
