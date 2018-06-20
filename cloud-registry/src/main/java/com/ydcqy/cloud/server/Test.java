package com.ydcqy.cloud.server;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * @author xiaoyu
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        int[] i = {1, 2, 3};
        System.out.println(i);
        log.info("123");
        ByteBuffer byteBuffer = ByteBuffer.wrap("哈".getBytes());
//        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println(byteBuffer.order());
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());
        System.out.println(Arrays.toString("哈".getBytes()));
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);

    }
}
