package com.ydcqy.ycache.cache;

import com.ydcqy.ycache.config.RedisConfig;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * @author xiaoyu
 */
@SuppressWarnings("unused")
public class RedisSupport {

    private JedisPool jedisPool;

    private volatile RedisConfig redisConfig;

    public RedisSupport() {
    }

    public RedisSupport(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
        init();
    }

    private void init() {
        checkConfig();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        if (redisConfig.getMinIdle() != null) jedisPoolConfig.setMinIdle(redisConfig.getMinIdle());
        if (redisConfig.getMaxIdle() != null) jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
        if (redisConfig.getMinEvictableIdleTimeMillis() != null)
            jedisPoolConfig.setMinEvictableIdleTimeMillis(redisConfig.getMinEvictableIdleTimeMillis());
        if (redisConfig.getMaxTotal() != null) jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
        if (redisConfig.getMaxWaitMillis() != null) jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
        if (redisConfig.getTestWhileIdle() != null) jedisPoolConfig.setTestWhileIdle(redisConfig.getTestWhileIdle());
        if (redisConfig.getTestOnBorrow() != null) jedisPoolConfig.setTestOnBorrow(redisConfig.getTestOnBorrow());
        if (redisConfig.getTestOnReturn() != null) jedisPoolConfig.setTestOnReturn(redisConfig.getTestOnReturn());
        if (redisConfig.getTimeBetweenEvictionRunsMillis() != null)
            jedisPoolConfig.setTimeBetweenEvictionRunsMillis(redisConfig.getTimeBetweenEvictionRunsMillis());
        if (StringUtils.isEmpty(redisConfig.getPassword()))
            jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getMaxWaitMillis());
        else
            jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getMaxWaitMillis(), redisConfig.getPassword());
    }

    private void checkConfig() {
        if (StringUtils.isBlank(redisConfig.getHost())) {
            throw new NullPointerException("Host must not be null");
        }
    }

    public void setRedisConfigAndInit(RedisConfig redisConfig) {
        if (this.redisConfig != null) {
            throw new RuntimeException("Redis is already initialized");
        }
        this.redisConfig = redisConfig;
        init();
    }

    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).set(key, value);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).get(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public Long del(String key) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).del(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public String lset(String key, long index, String value) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).lset(key, index, value);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public String lindex(String key, long index) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).lindex(key, index);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).lrange(key, start, end);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public Long lpush(String key, String... strings) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).lpush(key, strings);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public String lpop(String key) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).lpop(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public Long rpush(String key, String... strings) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).rpush(key, strings);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public String rpop(String key) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).rpop(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).exists(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public Long expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).expire(key, seconds);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }
}
