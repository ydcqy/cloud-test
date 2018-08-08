package com.ydcqy.ymq.spring.boot.autoconfigure;

import com.ydcqy.ymq.AbstractConfigurationFactory;
import com.ydcqy.ymq.spring.ConfigBean;
import com.ydcqy.ymq.spring.support.EnableYmq;
import com.ydcqy.ymq.spring.support.YmqScanRegistrar;
import com.ydcqy.ymq.spring.util.ActiveType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

/**
 * spring boot automatic configuration support {@link YmqProperties}
 *
 * @author xiaoyu
 */
@Configuration
@ConditionalOnClass({AbstractConfigurationFactory.class, ConfigBean.class})
@EnableConfigurationProperties(YmqProperties.class)
public class YmqAutoConfiguration {

    private YmqProperties properties;

    public YmqAutoConfiguration(YmqProperties properties) {
        this.properties = properties;
    }


    @Configuration
    @Import(AutoConfiguredYmqScanRegistrar.class)
//    @EnableYmq
    @ConditionalOnMissingBean(ConfigBean.class)
    @EnableConfigurationProperties(YmqProperties.class)
    public static class YmqScanRegistrarConfiguration {
        private YmqProperties properties;

        public YmqScanRegistrarConfiguration(YmqProperties properties) {
            this.properties = properties;
        }

        @Bean
        @ConditionalOnMissingBean
        public ConfigBean configBean() {
            System.out.println("哈哈");
            String active = properties.getActive();
            com.ydcqy.ymq.configuration.Configuration configuration = null;
            switch (active) {
                case ActiveType.ACTIVEMQ:
                    System.out.println(active);
                    break;
                case ActiveType.RABBITMQ:
                    System.out.println(active);
                    break;
                case ActiveType.KAFKA:
                    System.out.println(active);
                    break;
                default:
                    throw new IllegalArgumentException("ymq active type of error");
            }
            ConfigBean configBean = new ConfigBean();
            configBean.setConfiguration(configuration);
            configBean.setActive(active);
            return configBean;
        }
    }

    public static class AutoConfiguredYmqScanRegistrar extends YmqScanRegistrar implements BeanFactoryAware {
        private BeanFactory beanFactory;

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            List<String> packagesToScan = AutoConfigurationPackages.get(this.beanFactory);
            System.out.println("路径"+packagesToScan);
            for (String s : packagesToScan) {
                registerQueueBeanDefinitions(registry, s);
            }
            registerConsumerListenerProcessor(registry);
            registerProducerProcessor(registry);
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
            List<String> packagesToScan = AutoConfigurationPackages.get( beanFactory);
            System.out.println("路径"+packagesToScan);
        }
    }
}
