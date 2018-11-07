package com.ydcqy.grpc.autoconfigure;

import com.ydcqy.grpc.support.GrpcServiceContainer;
import io.grpc.BindableService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring boot自动配置支持 {@link GrpcProperties}
 *
 * @author xiaoyu
 */
@Configuration
@ConditionalOnClass({BindableService.class, GrpcServiceContainer.class})
@EnableConfigurationProperties(GrpcProperties.class)
public class GrpcAutoConfiguration {

    private GrpcProperties properties;

    public GrpcAutoConfiguration(GrpcProperties properties) {
        this.properties = properties;
    }


    @Bean
    @ConditionalOnMissingBean
    public GrpcServiceContainer grpcServiceContainer() throws Exception {
        GrpcServiceContainer container = new GrpcServiceContainer(properties.getServer().getPort());
        if (null != properties.getServer().getThreadNum()) {
            container.handleThreadPool(properties.getServer().getThreadNum());
        }
        return container;
    }

}
