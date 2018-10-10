package com.ydcqy.bigdata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author xiaoyu
 */
public class Test {
    public static void main(String[] args) {
        JSONObject obj = JSON.parseObject("{\"a\":123}");
        System.out.println(obj.getString("adsd")==null);
    }
}
