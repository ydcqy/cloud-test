package com.ydcqy.cloud.services.top;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@ImportResource("classpath:config/applicationContext.xml")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class TopServiceMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TopServiceMain.class, args);
        log.info("==========Spring Boot top.service启动成功！==========");
    }
}
