package com.ydcqy.cloud.services.talk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * @author xiaoyu
 */
@Slf4j
public class LogTest {
    public static void main(String[] args) throws Exception {
        RateLimiter rateLimiter = RateLimiter.create(100);
        long ss = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1);
            boolean acquire = rateLimiter.tryAcquire();
            if (acquire) {
                JSONObject obj = new JSONObject();
                obj.put("logType", "item");
                obj.put("userId", 123);
                obj.put("createTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
                obj.put("success", true);
                obj.put("body", "用户查询接口调用");
                log.info(JSON.toJSONString(obj));
            } else {
                JSONObject obj = new JSONObject();
                obj.put("logType", "item");
                obj.put("userId", 123);
                obj.put("createTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
                obj.put("success", false);
                obj.put("body", "超频，请稍后再试");
                log.info(JSON.toJSONString(obj));
            }
        }

        System.out.println(System.currentTimeMillis() - ss);
    }
}
