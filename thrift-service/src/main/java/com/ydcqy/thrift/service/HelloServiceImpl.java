package com.ydcqy.thrift.service;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaoyu
 */

public class HelloServiceImpl implements HelloService.AsyncIface {
    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Override
//    public String sayHi(String username, int age) throws TException {
//        logger.info("sayHi, username: {}, age: {}", username, age);
//        return "hi";
//    }

    @Override
    public void sayHi(String username, int age, AsyncMethodCallback<String> resultHandler) throws TException {
        logger.info("sayHi, username: {}, age: {}", username, age);
        resultHandler.onComplete("hi");
    }
}
