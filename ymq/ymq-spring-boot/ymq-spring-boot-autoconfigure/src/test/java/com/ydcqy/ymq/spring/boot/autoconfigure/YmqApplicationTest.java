package com.ydcqy.ymq.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @author xiaoyu
 */
@Slf4j
@SpringBootApplication
public class YmqApplicationTest implements BeanFactoryAware {
    public static void main(String[] args) {
        SpringApplication.run(YmqApplicationTest.class, args);
        log.info("==========Spring Boot YmqApplicationTest启动成功！==========");

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        List<String> packagesToScan = AutoConfigurationPackages.get( beanFactory);
        System.out.println("路径"+packagesToScan);
    }
}
