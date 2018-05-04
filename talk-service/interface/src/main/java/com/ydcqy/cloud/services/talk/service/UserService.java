package com.ydcqy.cloud.services.talk.service;

import com.ydcqy.cloud.services.talk.model.UserVo;
import com.ydcqy.cloud.services.talk.support.PageWrapper;

/**
 * @author xiaoyu
 */
public interface UserService {
    PageWrapper<UserVo> findByPage(int pageNum, int pageSize);
}
