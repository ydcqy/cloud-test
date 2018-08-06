package com.ydcqy.cloud.customer.support;

import com.ydcqy.cloud.customer.interceptor.MvcInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lenovo on 2018/1/23.
 */
@Slf4j
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private MvcInterceptor mvcInterceptor;
    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mvcInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
