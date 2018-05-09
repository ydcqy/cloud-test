package com.ydcqy.cloud.services.talk.dao.g1;

import com.github.pagehelper.Page;
import com.ydcqy.cloud.services.talk.model.UserPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author xiaoyu
 */
public interface G1UserDao {
    /**
     * 根据Id查用户
     *
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
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
    @Update("update user set username=#{newUsername} where id=#{id}")
    void updateUser(@Param("id") Integer id, @Param("newUsername") String newUsername);
}
