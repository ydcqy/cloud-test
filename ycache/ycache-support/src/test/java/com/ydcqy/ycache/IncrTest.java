package com.ydcqy.ycache;

import com.ydcqy.ycache.cache.RedisSupport;
import com.ydcqy.ycache.config.RedisConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaoyu
 */
public class IncrTest {
    public static void main(String[] args) {
        RedisSupport redisSupport = new RedisSupport(RedisConfig.loadByResource());

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    redisSupport.decr("available_quantity");
                }
            });
        }
    }

}
