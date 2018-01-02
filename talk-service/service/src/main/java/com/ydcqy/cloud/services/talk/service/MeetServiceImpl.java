package com.ydcqy.cloud.services.talk.service;

import com.ydcqy.cloud.services.talk.exception.TalkException;
import com.ydcqy.cloud.services.talk.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by xiaoyu on 2017/8/4.
 */
@Slf4j
@RestController
public class MeetServiceImpl implements MeetService {

    private ImageService imageService;

    public MeetServiceImpl() {
        log.info("MeetServiceImpl初始化");
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
        log.info("MeetServiceImpl设置依赖imageService");
    }

    @Override
    public void sayHello(String talkerName, String targetName) throws TalkException {
        log.info("{}：你好啊，{}！", talkerName, targetName);
        throw new TalkException("你有毒！");
    }

}
