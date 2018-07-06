package com.ydcqy.ycache.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class ZkClient {
    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("10.1.7.12:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("xxxxxxxxxxxxxxxxxxx"+event);
            }
        });
        System.out.println(zk.getChildren("/", null));
        System.out.println(new String(zk.getData("/abc", null, null)));
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(10));
        System.out.println(new String(zk.getData("/abc", null, null)));

    }
}
