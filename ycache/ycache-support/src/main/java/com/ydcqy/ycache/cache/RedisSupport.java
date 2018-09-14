package com.ydcqy.ycache.cache;

import com.ydcqy.ycache.cluster.Node;
import com.ydcqy.ycache.cluster.redis.ClusterJedisPool;
import com.ydcqy.ycache.cluster.redis.ClusterType;
import com.ydcqy.ycache.config.RedisConfig;
import com.ydcqy.ycache.exception.CacheConfigException;
import com.ydcqy.ycache.util.RedisLock;
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
        initPool(jedisPoolConfig);

        if (null != redisConfig.getCluster()) {
            initCluster(jedisPoolConfig);
        } else {
            if (StringUtils.isEmpty(redisConfig.getPassword()))
                jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getMaxWaitMillis());
            else
                jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getMaxWaitMillis(), redisConfig.getPassword());
        }
    }

    private void initPool(JedisPoolConfig jedisPoolConfig) {
        if (redisConfig.getMinIdle() != null) jedisPoolConfig.setMinIdle(redisConfig.getMinIdle());
        if (redisConfig.getMaxIdle() != null) jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
        if (redisConfig.getMinEvictableIdleTimeMillis() != null)
            jedisPoolConfig.setMinEvictableIdleTimeMillis(redisConfig.getMinEvictableIdleTimeMillis());
        if (redisConfig.getMaxTotal() != null) jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
        if (redisConfig.getMaxWaitMillis() != null)
            jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
        if (redisConfig.getTestWhileIdle() != null)
            jedisPoolConfig.setTestWhileIdle(redisConfig.getTestWhileIdle());
        if (redisConfig.getTestOnBorrow() != null) jedisPoolConfig.setTestOnBorrow(redisConfig.getTestOnBorrow());
        if (redisConfig.getTestOnReturn() != null) jedisPoolConfig.setTestOnReturn(redisConfig.getTestOnReturn());
        if (redisConfig.getTimeBetweenEvictionRunsMillis() != null)
            jedisPoolConfig.setTimeBetweenEvictionRunsMillis(redisConfig.getTimeBetweenEvictionRunsMillis());
    }

    private void initCluster(JedisPoolConfig jedisPoolConfig) {
        ClusterType type = ClusterType.as(redisConfig.getCluster().getType());
        List<Node> nodes = redisConfig.getCluster().getNodes();
        this.jedisPool = new ClusterJedisPool(jedisPoolConfig, nodes, type, redisConfig);
    }

    private void checkConfig() {
        RedisConfig.Cluster cluster = redisConfig.getCluster();
        if (cluster == null) {
            if (StringUtils.isBlank(redisConfig.getHost())) {
                throw new NullPointerException("Host must not be null");
            }
        } else {
            if (null == ClusterType.as(cluster.getType())) {
                throw new CacheConfigException("Redis cluster type is not supported");
            }
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

    public String set(String key, String value, String nxxx, String expx, long time) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).set(key, value, nxxx, expx, time);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public String set(byte[] key, byte[] value, byte[] nxxx, byte[] expx, long time) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).set(key, value, nxxx, expx, time);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public Boolean setbit(String key, long offset, boolean value) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).setbit(key, offset, value);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public Boolean getbit(String key, long offset) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).getbit(key, offset);
        } finally {
            if (null != jedis) jedis.close();
        }
    }


    public String select(int index) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).select(index);
        } finally {
            if (null != jedis) jedis.close();
        }
    }

    public Long setnx(String key, String value) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).setnx(key, value);
        } finally {
            if (null != jedis) jedis.close();
        }
    }

    private Jedis jedis;

    public Object eval(String script, List<String> keys, List<String> args) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).eval(script, keys, args);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public Long incrBy(String key, long integer) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).incrBy(key, integer);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public Long incr(String key) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).incr(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public Long decrBy(String key, long integer) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).decrBy(key, integer);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public Long decr(String key) {
        Jedis jedis = null;
        try {
            return (jedis = jedisPool.getResource()).decr(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public RedisLock getLock(String key, int expxSeconds) {

        RedisLock redisLock = new RedisLock(this, key, expxSeconds);
        return redisLock;
    }
}
