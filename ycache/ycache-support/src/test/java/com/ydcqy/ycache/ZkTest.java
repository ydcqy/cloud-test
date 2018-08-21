package com.ydcqy.ycache;

import com.ydcqy.ycache.util.ZkLock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class ZkTest {
    public static void main(String[] args) {
        ZkLock zkLock = new ZkLock("10.1.7.12:2181");
        LockSupport.park();
    }
}
