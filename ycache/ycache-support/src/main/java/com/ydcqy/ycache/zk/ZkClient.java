package com.ydcqy.ycache.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
@Slf4j
public class ZkClient {
    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (; ; ) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            log.info("====t2====");
                        }
                    }
                });
                t2.setDaemon(true);
                t2.start();
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("====t1====");
                }

            }
        });
        t1.start();
        LockSupport.park();
//        AbcZk abcZk = new AbcZk("10.1.7.12:2181");
//        abcZk.start();
//        LockSupport.park();
    }

    static class AbcZk implements Watcher {
        private ZooKeeper zk;
        private String    connectString;

        public AbcZk(String connectString) {
            this.connectString = connectString;
        }

        public void start() {
            try {
                zk = new ZooKeeper(connectString, 30000, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void process(WatchedEvent watchedEvent) {
            log.info("####事件####" + watchedEvent);
            try {
                log.info("状态：{} 数据：{}", zk.getState(), new String(zk.getData("/abc", true, null)));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
