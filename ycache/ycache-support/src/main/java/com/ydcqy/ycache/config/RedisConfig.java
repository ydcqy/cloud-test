package com.ydcqy.ycache.config;

import com.ydcqy.ycache.exception.CacheConfigException;
import com.ydcqy.ycache.util.ConfigUtils;
import lombok.Data;
import redis.clients.jedis.Protocol;

import java.util.HashMap;
import java.util.Properties;

/**
 * @author xiaoyu
 */
@Data
public class RedisConfig {

    private String host;
    private Integer port;
    private String password;
    private Integer minIdle;
    private Integer maxIdle;
    private Integer maxTotal;
    private Integer maxWaitMillis;
    private Integer minEvictableIdleTimeMillis;
    private Integer timeBetweenEvictionRunsMillis;
    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;

    public static RedisConfig loadByResource() {
        RedisConfig redisConfig = new RedisConfig();
        Properties properties = ConfigUtils.loadProperties("redis.properties");
        if (null == properties) {
            Object yaml = ConfigUtils.loadYaml("redis.yaml");
            if (null == yaml) {
                Object yml = ConfigUtils.loadYaml("redis.yml");
                if (null == yml) {
                    throw new CacheConfigException("Redis config Resource file does not exist");
                }
                yaml = yml;
            }
            if (!(yaml instanceof HashMap)) throw new CacheConfigException("redis yaml file format error");
            HashMap<String, Object> ymlObj = (HashMap<String, Object>) yaml;
            Object value;
            if ((value = ymlObj.get("host")) != null) redisConfig.host = String.valueOf(value);
            if ((value = ymlObj.getOrDefault("port", Protocol.DEFAULT_PORT)) != null)
                redisConfig.port = (Integer) value;
            if ((value = ymlObj.get("password")) != null) redisConfig.password = String.valueOf(value);
            if ((value = ymlObj.get("minIdle")) != null) redisConfig.minIdle = (Integer) value;
            if ((value = ymlObj.get("maxIdle")) != null) redisConfig.maxIdle = (Integer) value;
            if ((value = ymlObj.get("maxTotal")) != null) redisConfig.maxTotal = (Integer) value;
            if ((value = ymlObj.get("minEvictableIdleTimeMillis")) != null)
                redisConfig.minEvictableIdleTimeMillis = (Integer) value;
            if ((value = ymlObj.get("timeBetweenEvictionRunsMillis")) != null)
                redisConfig.timeBetweenEvictionRunsMillis = (Integer) value;
            if ((value = ymlObj.get("maxWaitMillis")) != null)
                redisConfig.maxWaitMillis = (Integer) value;
            if ((value = ymlObj.get("testWhileIdle")) != null)
                redisConfig.testWhileIdle = (Boolean) value;
            if ((value = ymlObj.get("testOnBorrow")) != null)
                redisConfig.testOnBorrow = (Boolean) value;
            if ((value = ymlObj.get("testOnReturn")) != null)
                redisConfig.testOnReturn = (Boolean) value;
        } else {
            String value;
            if ((value = properties.getProperty("host")) != null) redisConfig.host = value;
            if ((value = properties.getProperty("port", String.valueOf(Protocol.DEFAULT_PORT))) != null)
                redisConfig.port = Integer.valueOf(value);
            if ((value = properties.getProperty("password")) != null) redisConfig.password = value;
            if ((value = properties.getProperty("minIdle")) != null) redisConfig.minIdle = Integer.valueOf(value);
            if ((value = properties.getProperty("maxIdle")) != null) redisConfig.maxIdle = Integer.valueOf(value);
            if ((value = properties.getProperty("maxTotal")) != null) redisConfig.maxTotal = Integer.valueOf(value);
            if ((value = properties.getProperty("minEvictableIdleTimeMillis")) != null)
                redisConfig.minEvictableIdleTimeMillis = Integer.valueOf(value);
            if ((value = properties.getProperty("timeBetweenEvictionRunsMillis")) != null)
                redisConfig.timeBetweenEvictionRunsMillis = Integer.valueOf(value);
            if ((value = properties.getProperty("maxWaitMillis")) != null)
                redisConfig.maxWaitMillis = Integer.valueOf(value);
            if ((value = properties.getProperty("testWhileIdle")) != null)
                redisConfig.testWhileIdle = Boolean.valueOf(value);
            if ((value = properties.getProperty("testOnBorrow")) != null)
                redisConfig.testOnBorrow = Boolean.valueOf(value);
            if ((value = properties.getProperty("testOnReturn")) != null)
                redisConfig.testOnReturn = Boolean.valueOf(value);
        }
        return redisConfig;
    }

}
