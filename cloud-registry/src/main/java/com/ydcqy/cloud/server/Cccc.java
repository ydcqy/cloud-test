package com.ydcqy.cloud.server;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyDescriptor;

/**
 * @author xiaoyu
 */
@Configuration
public class Cccc extends InstantiationAwareBeanPostProcessorAdapter {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println(beanName + "###postProcessBeforeInstantiation");
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + "###postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + "###postProcessAfterInitialization");
        return bean;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + "###postProcessAfterInstantiation");
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println(beanName + "###postProcessPropertyValues");
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
}
