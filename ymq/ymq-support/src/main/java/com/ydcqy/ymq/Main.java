package com.ydcqy.ymq;

import com.ydcqy.ymq.util.UnsafeUtil;
import sun.misc.Unsafe;

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

    public static void main(String[] args) throws NoSuchFieldException {
//        ConnectionFactory cf = new ActiveMqConnectionFactory(new ActiveMqConfigurationFactory().getConfiguration());
//        Producer producer = new ActiveMqProducer(cf);

        Main obj = new Main();
//        System.out.println(unsafe.getAndAddInt(obj, valueOffset, 1));
        System.out.println(obj.value);
        System.out.println(unsafe.getObjectVolatile(obj, valueOffset));
        boolean b = unsafe.compareAndSwapObject(obj, valueOffset, obj.value, new Object());
        System.out.println(b);
        System.out.println(obj.value);

    }
}
