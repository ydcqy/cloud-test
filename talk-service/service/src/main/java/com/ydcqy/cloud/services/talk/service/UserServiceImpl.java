package com.ydcqy.cloud.services.talk.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydcqy.cloud.services.talk.dao.g1.G1UserDao;
import com.ydcqy.cloud.services.talk.dao.personalItem.UserDao;
import com.ydcqy.cloud.services.talk.model.UserPo;
import com.ydcqy.cloud.services.talk.model.UserVo;
import com.ydcqy.cloud.services.talk.support.PageWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;

/**
 * @author xiaoyu
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserDao userDao;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private G1UserDao g1UserDao;

    public UserPo getById(Integer id) {
        return userDao.getUserById(id);
    }

    public UserPo getG1ById(Integer id) {
        return g1UserDao.getUserById(id);
    }

    @Override
    public PageWrapper<UserVo> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<UserPo> userPoPage = userDao.findByPage();
        PageWrapper<UserVo> userVoPageWrapper = new PageWrapper<>();
        BeanUtils.copyProperties(userPoPage, userVoPageWrapper);
        return userVoPageWrapper;
    }

    @Autowired
    @Qualifier("g1SqlSessionFactory")
    private SqlSessionFactory g1SqlSessionFactory;

    @Transactional(rollbackFor = Exception.class, transactionManager = "personalItemTransactionManager")
    @Override
    public void updateUser(Integer id, String newUsername) {
        userDao.updateUser(id, newUsername);
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(g1SqlSessionFactory);
        currentProxy().updateG1User(id, newUsername);
        Connection connection = sqlSession.getConnection();
        int i = 1 / 0;
    }

    @Transactional(rollbackFor = Exception.class, transactionManager = "g1TransactionManager")
    public void updateG1User(Integer id, String newUsername) {
        g1UserDao.updateUser(id, newUsername);
    }

    private UserServiceImpl currentProxy() {

        return (UserServiceImpl) AopContext.currentProxy();
    }
}
