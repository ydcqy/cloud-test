package com.ydcqy.ymq.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaoyu
 */
@Data
@ConfigurationProperties(prefix = "ymq")
public class YmqProperties {
    /**
     * Specify mq type
     */
    private String   active;
    private Activemq activemq;
    private Rabbitmq rabbitmq;
    private Kafka    kafka;

    @Data
    public static class Activemq {
        /**
         * Activemq's connection string
         */
        private String brokerUrl;
        /**
         * Activemq's permissions users
         */
        private String username;
        /**
         * Activemq's users password
         */
        private String password;

        private ProducerPool     producerPool;
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

    @Data
    public static class Rabbitmq {
        private String           host;
        private Integer          port;
        private String           username;
        private String           password;
        private ProducerPool     producerPool;
        private ConsumerListener consumerListener;

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

    @Data
    public static class Kafka {
        private String           bootstrapServers;
        private ProducerPool     producerPool;
        private ConsumerListener consumerListener;

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
            private Integer batchSize;
            private Integer lingerMillis;
        }

        @Data
        public static class ConsumerListener {
            private Integer concurrency = 1;
        }
    }

}
