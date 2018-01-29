package com.ydcqy.cloud.services.top.service;

import com.ydcqy.cloud.services.top.exception.TopException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2018/1/12.
 */
@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService {
    @Override
    public String getTop10() throws TopException {
        log.info("今天天气真的很好！");
        return "今天天气真好！" + Thread.currentThread();
    }
}
