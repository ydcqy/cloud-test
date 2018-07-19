package com.ydcqy.ycache;

import com.ydcqy.ycache.cache.RedisSupport;
import com.ydcqy.ycache.config.RedisConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class Test {
    public static void main(String[] args) {
        RedisSupport redisSupport = new RedisSupport(RedisConfig.loadByResource());
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new Runnable() {
                @Override
                public void run() {
            long set = redisSupport.setnx("abc1212", "111");
            System.out.println(set + " " + finalI);
                }
            });
        }
        LockSupport.park();
    }
}
