package com.ydcqy.cloud.customer;

import com.ydcqy.cloud.customer.util.SpringUtils;
import com.ydcqy.cloud.services.talk.service.ImageService;
import com.ydcqy.cloud.services.talk.service.MeetService;
import com.ydcqy.cloud.services.top.exception.TopException;
import com.ydcqy.cloud.services.top.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ydcqy")
@SpringBootApplication(scanBasePackages = "com.ydcqy")
@EnableAspectJAutoProxy(exposeProxy = true)
@ImportResource("classpath:config/applicationContext.xml")
public class CustomerMain {

    public static void main(String[] args) {
        SpringApplication.run(CustomerMain.class, args);
        log.info("==========Spring Boot Customer启动成功!==========");
        MeetService meetService = SpringUtils.getCurrentApplicationContext().getBean(MeetService.class);
        ImageService imageService = SpringUtils.getBean(ImageService.class);
        System.out.println(meetService);
        System.out.println(imageService);
        try {
            meetService.sayHello("张三", "李四");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(SpringUtils.getBean(RecommendService.class).getTop10());
        } catch (TopException e) {
            e.printStackTrace();
        }
    }
}
