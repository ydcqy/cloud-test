package com.ydcqy.cloud.services.talk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydcqy.cloud.services.talk.dao.UserDao;
import com.ydcqy.cloud.services.talk.model.UserPo;
import com.ydcqy.cloud.services.talk.model.UserVo;
import com.ydcqy.cloud.services.talk.support.PageWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoyu
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserDao userDao;

    @Override
    public PageWrapper<UserVo> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<UserPo> userPoPage = userDao.findByPage(pageNum, pageSize);
        log.info("pageNum:{} pageSize:{} total:{}", userPoPage.getPageNum(), userPoPage.getPageSize(), userPoPage.getTotal());
        log.info("结果:{}", JSON.toJSONString(userPoPage, SerializerFeature.WriteMapNullValue));
        return null;
    }
}
