package com.ydcqy.cloud.services.talk.service;

import com.ydcqy.cloud.services.talk.exception.TalkException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xiaoyu on 2017-12-25.
 */
@FeignClient(value = "talk-service")
public interface MeetService {

    @RequestMapping(value = "sayHello")
    void sayHello(@RequestParam("talkerName") String talkerName, @RequestParam("targetName") String targetName) throws TalkException;

}
