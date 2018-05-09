package com.ydcqy.cloud.services.talk.dao.personalItem;

import com.github.pagehelper.Page;
import com.ydcqy.cloud.services.talk.model.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoyu
 */
public interface UserDao {
    /**
     * 根据Id查用户
     *
     * @param id
     * @return
     */
    UserPo getUserById(Integer id);

    /**
     * 分页查询
     *
     * @return
     */
    Page<UserPo> findByPage();

    /**
     * 修改
     *
     * @param id
     * @param newUsername
     */
    void updateUser(@Param("id") Integer id, @Param("newUsername") String newUsername);
}
