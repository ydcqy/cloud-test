package com.ydcqy.cloud.customer.controller.ribbon;

import com.alibaba.fastjson.JSON;
import com.ydcqy.cloud.customer.controller.feign.TestFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xiaoyu on 2017/10/16.
 */
@Slf4j
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TestFeignService testFeignService;

    @RequestMapping("/test")
    public String test() {
//        System.out.println(restTemplate);
        restTemplate.getForObject("http://test-service/abc", String.class);
        System.out.println(testFeignService);
        return testFeignService.test();
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new RestTemplate()));
    }
}
