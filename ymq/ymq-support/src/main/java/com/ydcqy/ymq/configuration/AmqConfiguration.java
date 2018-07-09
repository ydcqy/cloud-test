package com.ydcqy.ymq.configuration;

import com.ydcqy.ymq.AmqConfigurationFactory;
import com.ydcqy.ymq.ConfigurationFactory;
import lombok.Data;

/**
 * @author xiaoyu
 */
@Data
public class AmqConfiguration implements Configuration {
    private int maxThreadPoolSize;
    private User user;
    private String pas;

    public static void main(String[] args) {
        ConfigurationFactory cf = new AmqConfigurationFactory();
        Configuration configuration = cf.getConfiguration();
        System.out.println(configuration);
    }

    @Data
    public static class User {
        private String name;
    }
}
