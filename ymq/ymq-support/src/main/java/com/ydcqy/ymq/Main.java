package com.ydcqy.ymq;

import sun.reflect.Reflection;

import javax.crypto.SecretKey;

/**
 * @author xiaoyu
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<MyTest> myTestClass = MyTest.class;
        ClassLoader cl = null;
        cl.loadClass("abc");
        Class.forName(MyTest.class.getName());
    }
}
