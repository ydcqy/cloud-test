package com.ydcqy.ycache;

import com.ydcqy.ycache.cache.RedisSupport;
import com.ydcqy.ycache.config.RedisConfig;

import java.util.List;

/**
 * @author xiaoyu
 */
public class MqTest {
    public static void main(String[] args) throws InterruptedException {
        RedisSupport redisSupport = new RedisSupport(RedisConfig.loadByResource());
        redisSupport.rpush("test:mq", "哈哈哈");

        for (; ; ) {
            Thread.sleep(1000);
        List<String> blpop = redisSupport.blpop(5, "test:mq");
        System.out.println(blpop.get(1));
        }
    }
}
