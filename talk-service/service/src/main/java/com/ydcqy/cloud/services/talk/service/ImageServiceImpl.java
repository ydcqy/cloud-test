package com.ydcqy.cloud.services.talk.service;

import com.ydcqy.cloud.services.talk.exception.TalkException;
import com.ydcqy.cloud.services.talk.util.FileUtil;
import com.ydcqy.cloud.services.talk.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by xiaoyu on 2017-12-26.
 */
@Slf4j
@RestController
public class ImageServiceImpl implements ImageService {


    private MeetService meetService;

    private FileUtil fileUtil;

    public ImageServiceImpl() {
        log.info("ImageServiceImpl初始化");
    }

    @Autowired
    public void setMeetService(MeetService meetService) {
        this.meetService = meetService;
        log.info("ImageServiceImpl设置依赖meetService");
    }

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
        log.info("ImageServiceImpl设置依赖fileUtil");
    }

    @Override
    public String getHeadImageUrl(String userId) throws TalkException {
        return "http://avatar.csdn.net/E/4/A/1_muzizongheng.jpg";
    }

    @Override
    public byte[] getHeadImage(String imgUrl) throws TalkException {
        try {
            return ImageUtil.downloadFileData(imgUrl);
        } catch (IOException e) {
            throw new TalkException(e.getMessage(), e);
        }
    }
}
