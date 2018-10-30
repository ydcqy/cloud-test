package com.ydcqy.ynet.rpc;

import com.google.protobuf.StringValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaoyu
 */
public class HelloServiceImpl implements HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String sayHi(StringValue username) {
        logger.info("hello: {}", username.getValue());
        return "hi";
    }
}
