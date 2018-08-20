package com.ydcqy.ycache.util;

import com.ydcqy.ycache.cache.RedisSupport;

import java.io.Serializable;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * <b>Redis分布式锁</b>
 *
 * @author xiaoyu
 * @see Lock
 */
public class RedisLock implements Lock, Serializable {
    private static final String              LOCK_SUCCESS         = "OK";
    private static final Long                RELEASE_SUCCESS      = 1L;
    /**
     * NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
     * if it already exist.
     */
    private static final String              SET_IF_NOT_EXIST     = "NX";
    /**
     * EX|PX, expire time units: EX = seconds; PX = milliseconds
     */
    private static final String              SET_WITH_EXPIRE_TIME = "EX";
    private              ThreadLocal<String> lockValueHolder      = new ThreadLocal<>();
    private RedisSupport redisSupport;
    private String       lockKey;
    private int          seconds;

    public RedisLock(RedisSupport redisSupport, String lockKey, int expxSeconds) {
        this.redisSupport = redisSupport;
        this.lockKey = lockKey;
        this.seconds = expxSeconds;
    }

    @Override
    public void lock() {
        try {
            if (lockValueHolder.get() == null) {
                lockValueHolder.set(UUID.randomUUID().toString());
            } else {
                return;
            }
            int lookupIntervalMillis = 50;
            while (!LOCK_SUCCESS.equalsIgnoreCase(redisSupport.set(lockKey, lockValueHolder.get(), SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, seconds))) {
                try {
                    Thread.sleep(lookupIntervalMillis);
                } catch (InterruptedException e) {
                    throw e;
                }
            }

        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock() {
        if (lockValueHolder.get() == null) {
            String lockValue = UUID.randomUUID().toString();
            if (LOCK_SUCCESS.equalsIgnoreCase(redisSupport.set(lockKey, lockValue, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, seconds))) {
                lockValueHolder.set(lockValue);
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (lockValueHolder.get() == null) {
            int lookupIntervalMillis = 50;
            int waitingTimeMillis = 0;
            String lockValue = UUID.randomUUID().toString();
            while (!LOCK_SUCCESS.equalsIgnoreCase(redisSupport.set(lockKey, lockValue, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, seconds))) {
                try {
                    if (unit.toNanos(time) <= TimeUnit.MILLISECONDS.toNanos(waitingTimeMillis)) {
                        return false;
                    }
                    Thread.sleep(lookupIntervalMillis);
                    waitingTimeMillis += lookupIntervalMillis;
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            lockValueHolder.set(lockValue);
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void unlock() {
        if (lockValueHolder.get() == null) {
            throw new IllegalMonitorStateException();
        }
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = redisSupport.eval(script, Collections.singletonList(lockKey), Collections.singletonList(lockValueHolder.get()));
        lockValueHolder.remove();
        if (!RELEASE_SUCCESS.equals(result)) {
        }
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }
}
