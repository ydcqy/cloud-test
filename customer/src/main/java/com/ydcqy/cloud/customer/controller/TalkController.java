package com.ydcqy.cloud.customer.controller;

import com.ydcqy.cloud.services.talk.service.ImageService;
import com.ydcqy.cloud.services.talk.service.MeetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

}
