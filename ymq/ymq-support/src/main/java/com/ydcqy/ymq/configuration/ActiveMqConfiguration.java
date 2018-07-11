package com.ydcqy.ymq.configuration;

import com.google.common.util.concurrent.RateLimiter;
import com.ydcqy.ymq.ActiveMqConfigurationFactory;
import com.ydcqy.ymq.ConfigurationFactory;
import lombok.Data;

import java.util.concurrent.Semaphore;

/**
 * @author xiaoyu
 */
@Data
public class ActiveMqConfiguration implements Configuration {
    private String brokerUrl;
    private String user;
    private String password;

    public static void main(String[] args) {
        ConfigurationFactory cf = new ActiveMqConfigurationFactory();
        Configuration configuration = cf.getConfiguration();
        System.out.println(configuration);
        RateLimiter rateLimiter = RateLimiter.create(1);
        rateLimiter.acquire();
        System.out.println("11");
        rateLimiter.acquire();
        System.out.println("22");

    }

}
