package com.ydcqy.cloud.services.talk;

import com.ydcqy.cloud.services.talk.service.UserService;
import com.ydcqy.cloud.services.talk.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xiaoyu
 */
@Slf4j
@EnableEurekaClient
@MapperScan("com.ydcqy.cloud.services.talk.dao")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class TalkServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(TalkServiceMain.class, args);
        log.info("==========Spring Boot talk.service启动成功！==========");
        SpringUtils.getBean(UserService.class).findByPage(2, 10);

    }
}
