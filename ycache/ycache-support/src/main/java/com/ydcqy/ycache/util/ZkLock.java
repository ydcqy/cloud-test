package com.ydcqy.ycache.util;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Distributed lock for zookeeper
 *
 * @author xiaoyu
 * @see Lock
 */
@Slf4j
public class ZkLock implements Lock, Serializable {


    public ZkLock(String connectString) {

    }

    @Override
    public void lock() {

    }


    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
