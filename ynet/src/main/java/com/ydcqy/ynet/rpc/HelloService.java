package com.ydcqy.ynet.rpc;

import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;

/**
 * @author xiaoyu
 */
public interface HelloService {
    String sayHi(StringValue username, Int32Value age);
}
