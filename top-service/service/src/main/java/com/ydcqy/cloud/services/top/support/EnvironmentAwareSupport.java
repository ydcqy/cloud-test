package com.ydcqy.cloud.services.top.support;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @author xiaoyu
 */
@Configuration
public class EnvironmentAwareSupport implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("环境变量.........");
        System.out.println(JSON.toJSONString(environment.getActiveProfiles()));
        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(environment, "spring.");
        Map<String, Object> datasource = resolver.getSubProperties("datasource");
        System.out.println(JSON.toJSONString(datasource));
        System.out.println(JSON.toJSONString(resolver));
        System.out.println(JSON.toJSONString(resolver.getProperty("url")));
    }
}
