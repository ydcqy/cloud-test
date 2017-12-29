package com.ydcqy.cloud.services.talk.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by xiaoyu on 2017-12-29.
 */
@Slf4j
@Configuration
public class TestBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("1111，class:{}，name:{}", bean.getClass().getName(), beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("2222，class:{}，name:{}", bean.getClass().getName(), beanName);
        return bean;
    }
}
