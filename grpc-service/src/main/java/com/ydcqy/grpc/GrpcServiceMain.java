package com.ydcqy.grpc;

import com.ydcqy.grpc.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.web.context.ConfigurableWebApplicationContext;

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
        ConfigurableWebApplicationContext bean = SpringUtils.getBean(ConfigurableWebApplicationContext.class);
        System.out.println(bean);
    }
}
