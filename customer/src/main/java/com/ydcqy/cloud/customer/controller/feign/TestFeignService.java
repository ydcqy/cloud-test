package com.ydcqy.cloud.customer.controller.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiaoyu on 2017/11/23.
 */
@FeignClient(value = "test-service")
public interface TestFeignService {

    @RequestMapping("abc")
    String test();
}
