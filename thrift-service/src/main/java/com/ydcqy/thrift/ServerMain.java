package com.ydcqy.thrift;

import com.ydcqy.thrift.service.HelloService;
import com.ydcqy.thrift.service.HelloServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;

/**
 * @author xiaoyu
 */
public class ServerMain {
    public static void main(String[] args) throws Exception {
        TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(1111);

        TThreadedSelectorServer.Args args1 = new TThreadedSelectorServer.Args(serverTransport);
        args1.processor(new HelloService.AsyncProcessor(new HelloServiceImpl()));

        TServer server = new TThreadedSelectorServer(args1);
        System.out.println("启动成功");
        server.serve();
    }
}
