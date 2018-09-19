package com.ydcqy.ynet;

import com.ydcqy.ynet.server.SimpleServer;

import java.util.Objects;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class Test {
    public static void main(String[] args) {

        SimpleServer server = new SimpleServer(1111);
        LockSupport.park();
    }
}
