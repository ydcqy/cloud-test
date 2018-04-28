package com.ydcqy.cloud.services.talk.dao;

import com.ydcqy.cloud.services.talk.model.UserPo;

/**
 * @author xiaoyu
 */
public interface UserDao {
    UserPo getUserById(Integer id);
}
