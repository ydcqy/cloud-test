package com.ydcqy.grpc.support;

import io.grpc.BindableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * @author xiaoyu
 * @date 2017-12-29
 */
@Slf4j
@Configuration
public class GrpcServiceAnnotationBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware, ApplicationListener {
    private ApplicationContext ac;
    private GrpcServiceContainer grpcServiceContainer = new GrpcServiceContainer();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (hasAnnotation(bean, GrpcService.class)) {
            if (!(bean instanceof BindableService)) {
                throw new BeanNotOfRequiredTypeException(beanName, BindableService.class, bean.getClass());
            }
            grpcServiceContainer.append((BindableService) bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private <T extends Annotation> boolean hasAnnotation(Object obj, Class<T> annotationClass) {
        T annotation = obj.getClass().getAnnotation(annotationClass);
        return annotation != null ? true : false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent.getClass() == ContextRefreshedEvent.class) {
            if (grpcServiceContainer.size() > 0) {
                try {
                    grpcServiceContainer.start();
                } catch (IOException e) {
                    log.error(e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
