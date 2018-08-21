package com.ydcqy.ycache.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
@Slf4j
public class ZkClient {
    public static void main(String[] args) throws Exception {

        AbcZk abcZk = new AbcZk("10.1.7.12:2181");
        abcZk.start();
        LockSupport.park();
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
//                log.info("状态：{} 数据：{}", zk.getState(), new String(zk.getData("/abc", true, null)));
                log.info("状态：{} 数据：{}", zk.getState(), new String(zk.getData("/abc", true, null)));
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
