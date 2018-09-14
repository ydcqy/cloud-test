package com.ydcqy.ycache;

import com.ydcqy.ycache.util.RedisLock;
import com.ydcqy.ycache.cache.RedisSupport;
import com.ydcqy.ycache.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xiaoyu
 */
@Slf4j
public class LockTest {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(100);

        RedisSupport redisSupport = new RedisSupport(RedisConfig.loadByResource());

        RedisLock redisLock = redisSupport.getLock("abc-lock", 30);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        redisLock.lock();
                        log.info("获取到锁 {}");
                        Thread.sleep(5000);
                        redisLock.unlock();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
