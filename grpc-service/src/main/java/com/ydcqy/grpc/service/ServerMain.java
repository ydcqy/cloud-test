package com.ydcqy.grpc.service;

import com.ydcqy.grpc.support.GrpcServiceContainer;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class ServerMain {
    public static void main(String[] args) throws IOException {
        GrpcServiceContainer container = new GrpcServiceContainer(1111);
        container.append(new HelloServiceImpl());
        container.start();
        LockSupport.park();
    }
}
