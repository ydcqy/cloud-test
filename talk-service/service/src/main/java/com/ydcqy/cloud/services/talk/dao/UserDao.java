package com.ydcqy.cloud.services.talk.dao;

import com.github.pagehelper.Page;
import com.ydcqy.cloud.services.talk.model.UserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xiaoyu
 */
public interface UserDao {
    UserPo getUserById(Integer id);

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<UserPo> findByPage(int pageNum, int pageSize);
}
