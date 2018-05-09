package com.ydcqy.cloud.services.talk;

import com.ydcqy.cloud.services.talk.service.UserService;
import com.ydcqy.cloud.services.talk.service.UserServiceImpl;
import com.ydcqy.cloud.services.talk.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author xiaoyu
 */
@Slf4j
@EnableEurekaClient
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
public class TalkServiceMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TalkServiceMain.class, args);
        log.info("==========Spring Boot talk.service启动成功！==========");
        UserServiceImpl userService = (UserServiceImpl) SpringUtils.getBean(UserService.class);

        System.out.println(userService.getById(1));
        System.out.println(userService.getG1ById(1));
        userService.updateUser(1, "哈哈哈");
        System.out.println(userService.getById(1));
        System.out.println(userService.getG1ById(1));
    }
}
