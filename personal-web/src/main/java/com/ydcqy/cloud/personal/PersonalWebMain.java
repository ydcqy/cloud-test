package com.ydcqy.cloud.personal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author xiaoyu
 */
@Slf4j
@ImportResource("classpath:applicationContext.xml")
@SpringBootApplication
public class PersonalWebMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PersonalWebMain.class, args);
        log.info("==========Spring Boot personal-web启动成功！==========");
    }
}
