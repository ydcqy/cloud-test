package com.ydcqy.cloud.services.talk.controller;

import com.ydcqy.cloud.services.talk.model.UserVo;
import com.ydcqy.cloud.services.talk.service.UserService;
import com.ydcqy.cloud.services.talk.support.PageWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoyu
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findByPage")
    public PageWrapper findByPage(Integer pageNum, Integer pageSize) {
        PageWrapper<UserVo> page = userService.findByPage(pageNum, pageSize);
        return page;
    }
}
