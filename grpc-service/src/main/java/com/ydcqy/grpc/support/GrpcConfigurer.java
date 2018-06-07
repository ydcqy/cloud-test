package com.ydcqy.grpc.support;

import com.ydcqy.grpc.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;

/**
 * @author xiaoyu
 */
@Slf4j
@Configuration
public class GrpcConfigurer implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("###GrpcConfigurer####");
        System.out.println(beanFactory);
        Iterator<String> beanNamesIterator = beanFactory.getBeanNamesIterator();
        while (beanNamesIterator.hasNext()) {
            String next = beanNamesIterator.next();
            System.out.println(next);
        }
        DefaultListableBeanFactory bf = (DefaultListableBeanFactory) beanFactory;
        GenericBeanDefinition bdf = new GenericBeanDefinition();
        bdf.setBeanClass(UserServiceImpl.class);

        bf.registerBeanDefinition("abc", bdf);
    }
}
