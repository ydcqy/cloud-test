package com.ydcqy.ycache.config;

import lombok.Data;
import redis.clients.jedis.Protocol;

/**
 * @author xiaoyu
 */
@Data
public class RedisConfig {

    private String host;
    private int port = Protocol.DEFAULT_PORT;
    private int timeout = Protocol.DEFAULT_TIMEOUT;
    private String password;

    private RedisConfig() {
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public final static class Builder {
        private String host;
        private int port = Protocol.DEFAULT_PORT;
        private int timeout = Protocol.DEFAULT_TIMEOUT;
        private String password;

        private Builder() {
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public RedisConfig build() {
            RedisConfig redisConfig = new RedisConfig();
            redisConfig.setHost(this.host);
            redisConfig.setPort(this.port);
            redisConfig.setTimeout(this.timeout);
            redisConfig.setPassword(this.password);
            return redisConfig;
        }
    }

}
