package com.ydcqy.kiter.network;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 2018/1/27.
 */
@Slf4j
public class Test {
    @Data
    static class Abc {
        private String name;
        private int age;
        private Object obj;
    }

    public static void main(String[] args) {

    }
}
