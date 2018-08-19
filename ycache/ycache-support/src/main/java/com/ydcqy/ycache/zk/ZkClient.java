package com.ydcqy.ycache.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
@Slf4j
public class ZkClient {
    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper("10.1.7.12:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                log.info("####事件####" + event);
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        ZooKeeper.States state = zk.getState();
        log.info("状态" + state);
        byte[] data = zk.getData("/abc", true, null);
        log.info("结果：" + new String(data));
        LockSupport.park();
    }
}
