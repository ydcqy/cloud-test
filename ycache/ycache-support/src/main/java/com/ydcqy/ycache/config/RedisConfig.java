package com.ydcqy.ycache.config;

import com.ydcqy.ycache.cluster.Node;
import com.ydcqy.ycache.cluster.redis.RedisNode;
import com.ydcqy.ycache.exception.CacheConfigException;
import com.ydcqy.ycache.util.ConfigUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Cluster cluster;

    public static RedisConfig loadByResource() {
        RedisConfig redisConfig = new RedisConfig();
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
        String clusterType = null;
        Map<String, Object> tmp = (Map) ymlObj.get("cluster");
        if (tmp != null) {
            redisConfig.cluster = new Cluster();
            if (StringUtils.isNotBlank((clusterType = String.valueOf(tmp.get("type")))))
                redisConfig.cluster.type = clusterType;
            List<Map<String, Object>> nodes = (List<Map<String, Object>>) tmp.get("nodes");
            for (Map<String, Object> node : nodes) {
                RedisNode n = new RedisNode();
                if ((value = node.get("host")) != null) {
                    n.setHost(String.valueOf(value));
                }
                if ((value = node.get("port")) != null) {
                    n.setPort((Integer) value);
                }
                if ((value = node.get("password")) != null) {
                    n.setPassword(String.valueOf(value));
                }
                redisConfig.cluster.nodes.add(n);
            }
        }
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
        return redisConfig;
    }

    @Data
    static class Cluster {
        private String type;
        private List<Node> nodes = new ArrayList<>();
    }

    public static void main(String[] args) {
        loadByResource();
    }
}
