package com.ydcqy.ycache;

import com.ydcqy.ycache.cache.RedisSupport;
import com.ydcqy.ycache.config.RedisConfig;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class Test {
    public static void main(String[] args) {
        RedisSupport redisSupport = new RedisSupport(RedisConfig.newBuilder().host("10.1.7.11").build());
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 1000; i++)
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String abc1 = redisSupport.get("abc");
                    System.out.println(abc1);
                }
            });
        LockSupport.park();
    }
}
