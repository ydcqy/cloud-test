package com.ydcqy.cloud.services.top.support;

import com.alibaba.fastjson.JSON;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author xiaoyu
 */
@Configuration
public class EnvironmentAwareSupport implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("环境变量.........");
        System.out.println(JSON.toJSONString(environment.getActiveProfiles()));
    }
}
