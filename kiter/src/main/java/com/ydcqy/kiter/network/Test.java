package com.ydcqy.kiter.network;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by lenovo on 2018/1/27.
 */
public class Test {
    @Data
    static class Abc {
        private String name;
        private int age;
        private Object obj;
    }

    public static void main(String[] args) {
        Abc abc = new Abc();
        abc.setName("张三");
        abc.setAge(12);
        abc.setObj(new String[]{"a", "b", "c"});
        System.out.println(abc + " " + System.identityHashCode(abc.getObj()));
        Abc target = new Abc();
        BeanUtils.copyProperties(abc, target);
        System.out.println(target+" "+System.identityHashCode(target.getObj()));


    }
}
