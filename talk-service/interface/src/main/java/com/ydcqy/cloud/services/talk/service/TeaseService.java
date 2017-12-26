package com.ydcqy.cloud.services.talk.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoyu on 2017-12-25.
 */
@FeignClient(value = "talk-service")
public interface TeaseService {
}
