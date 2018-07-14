package com.ydcqy.grpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

/**
 * @author xiaoyu
 */
@Slf4j
@ComponentScan("com.ydcqy")
@SpringBootApplication
public class GrpcServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(GrpcServiceMain.class, args);
        log.info("==========Spring Boot grpc.service启动成功！==========");

    }
}
