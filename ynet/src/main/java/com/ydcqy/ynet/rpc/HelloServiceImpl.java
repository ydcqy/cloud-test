package com.ydcqy.ynet.rpc;

import com.google.protobuf.StringValue;

/**
 * @author xiaoyu
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHi(StringValue username) {
        System.out.println("hello: " + username.getValue());
        return "hi";
    }
}
