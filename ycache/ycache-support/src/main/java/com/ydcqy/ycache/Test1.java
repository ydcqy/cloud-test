package com.ydcqy.ycache;

import com.ydcqy.ycache.cluster.LoadBalance;
import com.ydcqy.ycache.cluster.Node;
import com.ydcqy.ycache.cluster.loadbalance.RandomLoadBalance;

import java.util.HashMap;

/**
 * @author xiaoyu
 */
public class Test1 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        HashMap map = new HashMap(3);
        map.put("abc", 123);
        System.out.println(map.get("abc"));
        String[] abc = new String[]{"abc"};
        System.out.println(abc.getClass().getInterfaces()[0]);
    }
}
