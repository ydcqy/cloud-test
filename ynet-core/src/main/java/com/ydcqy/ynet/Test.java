package com.ydcqy.ynet;

import com.ydcqy.ynet.server.SimpleServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class Test {
    static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        SimpleServer server = new SimpleServer(1111);
        System.out.println("启动结果：" + server.isOpen());
        for (; ; ) {
            try {
                Thread.sleep(1000);
                System.out.println(server.clientCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
