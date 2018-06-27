package com.ydcqy.ycache.cluster.redis;

import com.alibaba.fastjson.JSON;
import com.ydcqy.ycache.cluster.LoadBalance;
import com.ydcqy.ycache.cluster.Node;
import com.ydcqy.ycache.cluster.loadbalance.RandomLoadBalance;
import com.ydcqy.ycache.config.RedisConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoyu
 */
public class ClusterJedisPool extends JedisPool {
    private Map<Node, JedisPool> jedisPoolMap = new HashMap<>(1 << 4);
    private LoadBalance loadBalance = new RandomLoadBalance();

    public ClusterJedisPool(GenericObjectPoolConfig jedisPoolConfig, List<Node> clusterNodes, ClusterType clusterType, RedisConfig redisConfig) {
        switch (clusterType) {
            case CODIS:
                initCodis(jedisPoolConfig, clusterNodes, clusterType, redisConfig);
                break;
            case REDIS_CLUSTER:
                initRedisCluster(jedisPoolConfig, clusterNodes, clusterType, redisConfig);
                break;
            case NONE:
                if (StringUtils.isEmpty(redisConfig.getPassword()))
                    jedisPoolMap.put(null, new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getMaxWaitMillis()));
                else
                    jedisPoolMap.put(null, new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getMaxWaitMillis(), redisConfig.getPassword()));
                break;
        }
    }

    private void initCodis(GenericObjectPoolConfig jedisPoolConfig, List<Node> clusterNodes, ClusterType clusterType, RedisConfig redisConfig) {
        for (Node node : clusterNodes) {
            loadBalance.addNode(node);
            if (StringUtils.isEmpty(redisConfig.getPassword()))
                jedisPoolMap.put(node, new JedisPool(jedisPoolConfig, node.getHost(), node.getPort(), redisConfig.getMaxWaitMillis()));
            else
                jedisPoolMap.put(node, new JedisPool(jedisPoolConfig, node.getHost(), node.getPort(), redisConfig.getMaxWaitMillis(), ((RedisNode) node).getPassword()));
        }
    }

    private void initRedisCluster(GenericObjectPoolConfig jedisPoolConfig, List<Node> clusterNodes, ClusterType clusterType, RedisConfig redisConfig) {
        List<JedisShardInfo> shards = new ArrayList<>();
        //集群节点处理
        JedisCluster jedisCluster = new JedisCluster(new HashSet<>(), 1, jedisPoolConfig);
        for (Node node : clusterNodes) {
            if (StringUtils.isEmpty(redisConfig.getPassword())) {
                JedisShardInfo shardInfo = new JedisShardInfo(node.getHost(), node.getPort(), redisConfig.getMaxWaitMillis());
                shards.add(shardInfo);
            } else {
                JedisShardInfo shardInfo = new JedisShardInfo(node.getHost(), node.getPort(), redisConfig.getMaxWaitMillis());
                shardInfo.setPassword(redisConfig.getPassword());
                shards.add(shardInfo);
            }
        }
        //***//
        ShardedJedisPool jedisPool = new ShardedJedisPool(jedisPoolConfig, shards);
//        jedisPools.add(jedisPool);
    }

    @Override
    public Jedis getResource() {
        return jedisPoolMap.get(loadBalance.select()).getResource();
    }

    @Override
    public void returnBrokenResource(Jedis resource) {
        super.returnBrokenResource(resource);
    }

    @Override
    public void returnResource(Jedis resource) {
        super.returnResource(resource);
    }

    static class MeJedis {

    }
}
