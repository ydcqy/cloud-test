package com.ydcqy.cloud.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * @author xiaoyu
 */
@Slf4j
//@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ydcqy")
@SpringBootApplication(scanBasePackages = "com.ydcqy")
@EnableAspectJAutoProxy(exposeProxy = true)
@ImportResource("classpath:config/applicationContext.xml")
public class CustomerMain {

    public static void main(String[] args) {
        SpringApplication.run(CustomerMain.class, args);
        log.info("==========Spring Boot Customer启动成功!==========");
    }

}
