package com.ydcqy.ymq.spring.support;

import com.ydcqy.ymq.spring.ConfigBean;
import com.ydcqy.ymq.spring.ConsumerListenerBean;
import com.ydcqy.ymq.spring.annotation.ConsumerListener;
import com.ydcqy.ymq.spring.annotation.Queue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * @author xiaoyu
 */
public class ConsumerListenerAnnotationBeanPostProcessor implements BeanDefinitionRegistryPostProcessor, BeanClassLoaderAware {
    private ClassLoader classLoader;

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String[] definitionNames = registry.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(definitionName);
            String beanClassName = beanDefinition.getBeanClassName();
            Class<?> beanClass;
            try {
                beanClass = ClassUtils.forName(beanClassName, classLoader);
                ConsumerListener annotation = AnnotationUtils.findAnnotation(beanClass, ConsumerListener.class);
                if (null == annotation) {
                    continue;
                }
                Class<?>[] interfacesForClass = ClassUtils.getAllInterfacesForClass(beanClass);
                if (null == interfacesForClass || interfacesForClass.length == 0) {
                    throw new IllegalStateException("The listener needs at least one queue interface");
                }
                Map<Queue, Class<?>> queueClassMap = new HashMap<Queue, Class<?>>();
                for (Class<?> clazz : interfacesForClass) {
                    Queue queue = AnnotationUtils.findAnnotation(clazz, Queue.class);
                    if (queue != null) {
                        queueClassMap.put(queue, clazz);
                    }
                }
                if (queueClassMap.size() == 0) {
                    throw new IllegalStateException("The listener needs at least one queue interface");
                }
                registerListenerBeanDefinitions(definitionName, queueClassMap, registry);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    private void registerListenerBeanDefinitions(String implementRef, Map<Queue, Class<?>> queueClassMap, BeanDefinitionRegistry registry) {
        for (Queue queue : queueClassMap.keySet()) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(ConsumerListenerBean.class);
            builder.addPropertyReference("implementRef", implementRef)
                    .addPropertyReference("queueRef", queueClassMap.get(queue).getName())
                    .addPropertyReference("configBean", ConfigBean.CONFIG_BEAN_ID);
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }
}
