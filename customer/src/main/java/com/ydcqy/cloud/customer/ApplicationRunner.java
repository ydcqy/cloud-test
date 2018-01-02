package com.ydcqy.cloud.customer;

import com.ydcqy.cloud.customer.controller.feign.TestFeignService;
import com.ydcqy.cloud.customer.controller.ribbon.TestController;
import com.ydcqy.cloud.customer.util.SpringUtils;
import com.ydcqy.cloud.services.talk.exception.TalkException;
import com.ydcqy.cloud.services.talk.service.ImageService;
import com.ydcqy.cloud.services.talk.service.MeetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.util.FileCopyUtils;

import java.io.File;

@Slf4j
@EnableFeignClients(basePackages = "com.ydcqy")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.ydcqy")
public class ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
        log.info("==========Spring Boot Customer启动成功!==========");
        MeetService meetService = SpringUtils.getBean(MeetService.class);
        ImageService imageService = SpringUtils.getBean(ImageService.class);
        System.out.println(meetService);
        System.out.println(imageService);
        try {
            meetService.sayHello("张三", "李四");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
