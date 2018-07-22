package com.ydcqy.grpc.service.impl;

import java.util.BitSet;
import java.util.TreeSet;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class BindingKey {
    public static final String PRODUCT_STOCK_KEY = "product.stock.*";
    public static final String PRODUCT_PRICE_KEY = "product.price.*";

    public static void main(String[] args) {
        TreeSet ts=new TreeSet();
        ts.add(1);
        ts.add(2);
        ts.add(5);
        ts.add(3);
        System.out.println(ts);
    }
}
