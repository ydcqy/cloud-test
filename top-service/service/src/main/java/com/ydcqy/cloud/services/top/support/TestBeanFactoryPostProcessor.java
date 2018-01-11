package com.ydcqy.cloud.services.top.support;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

/**
 * Created by xiaoyu on 2017-12-28.
 */
@Slf4j
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info(JSON.toJSONString(beanFactory));
//        BeanDefinition meetServiceImpl = beanFactory.getBeanDefinition("meetServiceImpl");
//        System.out.println(meetServiceImpl);
        System.out.println("哈哈哈哈....");
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
