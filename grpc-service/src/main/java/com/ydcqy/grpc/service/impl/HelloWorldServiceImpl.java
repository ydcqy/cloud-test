package com.ydcqy.grpc.service.impl;

import com.ydcqy.grpc.rpc.HelloReply;
import com.ydcqy.grpc.rpc.HelloRequest;
import com.ydcqy.grpc.rpc.HelloWorldServiceGrpc;
import com.ydcqy.grpc.support.GrpcService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author xiaoyu
 */
@Slf4j
@GrpcService("helloWorldService")
public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    @Autowired
    private StringRedisTemplate redisTemplate;
//    @Autowired
//    private MongoTemplate mongoTemplate;
//    @Autowired
//    private RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void init() {
        redisTemplate.opsForValue().set("user:user_session", UUID.randomUUID().toString());
//        mongoTemplate.insert("{abdg:123,xxx:1234}", "user");
//        List<Object> user = mongoTemplate.findAll(Object.class, "user");
//        rabbitTemplate.setQueue("talk.o2o.sayhello");
//        rabbitTemplate.convertAndSend("grpc.xy.test", "talk.o2o.sayhello", "halo123");
//        System.out.println(user);
//        System.out.println(mongoTemplate + "哈哈哈2");
        System.out.println(redisTemplate + "哈哈哈l");
    }


    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        String name = request.getName();
        log.info("receive msg 【{}】", name);
        responseObserver.onNext(HelloReply.newBuilder().setMessage("ack:" + name).build());
        String s = name + UUID.randomUUID().toString();
        redisTemplate.opsForSet().add("say_hello", s);
//        mongoTemplate.insert("{msg:\"" + s + "\"}", "say_hello");
        responseObserver.onCompleted();
    }

}
