package com.ydcqy.cloud.customer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ydcqy.cloud.services.talk.service.ImageService;
import com.ydcqy.cloud.services.talk.service.MeetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiaoyu on 2017-12-26.
 */
@Slf4j
@RestController
public class TalkController {
    @Autowired
    private MeetService meetService;
    @Autowired
    private ImageService imageService;

    @RequestMapping("talk")
    public void talk(@RequestBody TalkRequestForm form) {
        log.info(JSON.toJSONString(form, SerializerFeature.WriteMapNullValue));
        log.info(form.getTime().toString());
        log.info("talk.....");
    }

    @RequestMapping("test")
    public String test() {
        System.out.println("dddddd");
        log.info("testxxxxx......");
        return "test结果";
    }

}