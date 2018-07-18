package com.ydcqy.ymq.rabbitmq;

import com.ydcqy.ymq.configuration.Configuration;
import lombok.Data;

/**
 * @author xiaoyu
 */

@Data
public class RabbiMqMqConfiguration implements Configuration {
    private String brokerUrl;
    private String username;
    private String password;
    private ProducerPool producerPool;
    private ConsumerListener consumerListener;

    @Data
    public static class ProducerPool {
        private Integer maxConnections;
        private Integer idleTimeout;
        private Integer expiryTimeout;
        private Integer timeBetweenExpirationCheckMillis;
    }

    @Data
    public static class ConsumerListener {
        private Integer concurrency = 1;
    }
}
