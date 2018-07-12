package com.ydcqy.ymq.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xiaoyu
 */
public class UnsafeUtil {
    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
