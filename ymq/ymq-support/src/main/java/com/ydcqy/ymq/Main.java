package com.ydcqy.ymq;

import com.ydcqy.ymq.connection.ActiveMqConnectionFactory;
import com.ydcqy.ymq.exception.MqException;
import com.ydcqy.ymq.message.ActiveMqMessage;
import com.ydcqy.ymq.message.ActiveMqQueue;
import com.ydcqy.ymq.producer.ActiveMqProducer;
import com.ydcqy.ymq.producer.Producer;
import com.ydcqy.ymq.util.UnsafeUtil;
import sun.misc.Unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaoyu
 */
public class Main {
    private static final Unsafe unsafe = UnsafeUtil.getUnsafe();

    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private volatile Object value = new Object();

    public static void main(String[] args) throws Exception {
        Producer producer = new ActiveMqProducer(new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration()));
        AtomicInteger n = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 10000; i++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        producer.send(new ActiveMqQueue("com.test", ActiveMqQueue.Type.QUEUE), new ActiveMqMessage("哇卡卡卡"));
                    } catch (MqException e) {
                        e.printStackTrace();
                    }
                    System.out.println(n.incrementAndGet());
                }
            });
        }


    }
}
