package com.ydcqy.cloud.services.top.service;

import com.ydcqy.cloud.services.top.exception.TopException;

/**
 * Created by lenovo on 2018/1/12.
 */
public interface RecommendService {
    String getTop10() throws TopException;
}
