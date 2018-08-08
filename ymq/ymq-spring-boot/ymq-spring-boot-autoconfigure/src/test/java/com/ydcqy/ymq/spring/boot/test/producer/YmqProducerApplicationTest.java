package com.ydcqy.ymq.spring.boot.test.producer;

import com.ydcqy.ymq.spring.boot.test.producer.service.TestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author xiaoyu
 */
@Slf4j
@SpringBootApplication
public class YmqProducerApplicationTest implements ApplicationContextAware {
    private static ApplicationContext ac;

    public static void main(String[] args) {
        SpringApplication.run(YmqProducerApplicationTest.class, args);
        log.info("==========Spring Boot YmqApplicationTest启动成功！==========");
        System.out.println(ac.getBean(TestServiceImpl.class));
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.ac = ac;
    }
}
