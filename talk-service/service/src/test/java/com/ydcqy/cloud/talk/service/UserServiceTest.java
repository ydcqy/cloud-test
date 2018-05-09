package com.ydcqy.cloud.talk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ydcqy.cloud.services.talk.TalkServiceMain;
import com.ydcqy.cloud.services.talk.model.UserVo;
import com.ydcqy.cloud.services.talk.service.UserService;
import com.ydcqy.cloud.services.talk.support.PageWrapper;
import com.ydcqy.cloud.talk.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiaoyu
 */
@Slf4j

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void findByPage() throws Exception {
        PageWrapper<UserVo> byPage = userService.findByPage(10, 10);
        log.info("结果：{}", JSON.toJSONString(byPage, SerializerFeature.WriteMapNullValue));
        log.info("#####通过#####");
    }

    @Test
    public void updateUser() throws Exception {
        userService.updateUser(10, "xiaoyu");
        log.info("#####通过#####");
    }

}
