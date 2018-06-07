package com.ydcqy.grpc;

import com.ydcqy.grpc.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.StopWatch;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;

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
        System.out.println(SpringUtils.getBean(StopWatch.class));


    }
}
