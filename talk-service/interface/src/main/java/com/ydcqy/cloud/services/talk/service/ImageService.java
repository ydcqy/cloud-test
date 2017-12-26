package com.ydcqy.cloud.services.talk.service;

import com.ydcqy.cloud.services.talk.exception.TalkException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xiaoyu on 2017-12-26.
 */
@FeignClient(value = "talk-service")
public interface ImageService {
    @RequestMapping(value = "getHeadImageUrl")
    String getHeadImageUrl(@RequestParam("userId") String userId) throws TalkException;

    @RequestMapping(value = "getHeadImage")
    byte[] getHeadImage(@RequestParam("imgUrl") String imgUrl) throws TalkException;
}
