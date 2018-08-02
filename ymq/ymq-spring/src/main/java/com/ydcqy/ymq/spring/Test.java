package com.ydcqy.ymq.spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiaoyu
 */
public class Test {
    public static void main(String[] args) {
        final AbcImpl targetObj = new AbcImpl();
        Abc abc = (Abc) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Abc.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理...");
                return method.invoke(targetObj, args);
            }
        });
        abc.a();
    }


    static class AbcImpl implements Abc {
        public void a() {
            System.out.println("a...");
           this.b();
        }

        public void b() {
            System.out.println("b...");
        }

    }

    interface Abc {
        void a();

        void b();
    }
}
