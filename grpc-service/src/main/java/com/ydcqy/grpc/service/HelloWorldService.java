package com.ydcqy.grpc.service;

import com.ydcqy.grpc.exception.GrpcException;

/**
 * @author xiaoyu
 */
public interface HelloWorldService {
    String sayHello(String name) throws GrpcException;
}
