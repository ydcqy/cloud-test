package com.ydcqy.cloud.services.talk;

import com.alibaba.fastjson.JSON;
import com.ydcqy.cloud.services.talk.dao.UserDao;
import com.ydcqy.cloud.services.talk.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@EnableEurekaClient
@MapperScan("com.ydcqy.cloud.services.talk.dao")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class TalkServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(TalkServiceMain.class, args);
        log.info("==========Spring Boot talk.service启动成功！==========");
        UserDao bean = SpringUtils.getBean(UserDao.class);
        System.out.println(JSON.toJSONString(bean.getUserById(1)));
        System.out.println(JSON.toJSONString(bean.getUserById(5)));
    }
}
