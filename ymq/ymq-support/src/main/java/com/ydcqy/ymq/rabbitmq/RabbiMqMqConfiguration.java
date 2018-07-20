package com.ydcqy.ymq.rabbitmq;

import com.ydcqy.ymq.configuration.Configuration;
import lombok.Data;

/**
 * @author xiaoyu
 */

@Data
public class RabbiMqMqConfiguration implements Configuration {
    private String host;
    private String port;
    private String username;
    private String password;

    @Data
    public static class ProducerPool {
        private Integer maxTotal;
        private Integer maxIdle;
        private Integer minIdle;
        private Integer maxWaitMillis;
        private Integer minEvictableIdleTimeMillis;
        private Integer timeBetweenEvictionRunsMillis;
        private Boolean testOnBorrow;
        private Boolean testOnReturn;
        private Boolean testWhileIdle;
    }

    @Data
    public static class ConsumerListener {
        private Integer concurrency = 1;
    }

}
