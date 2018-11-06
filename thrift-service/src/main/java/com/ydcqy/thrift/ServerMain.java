package com.ydcqy.thrift;

import com.ydcqy.thrift.service.HelloService;
import com.ydcqy.thrift.service.HelloServiceImpl;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * @author xiaoyu
 */
public class ServerMain {
    public static void main(String[] args) throws Exception {
        TThreadedSelectorServer.Args args1 = new TThreadedSelectorServer.Args(new TNonblockingServerSocket(1111));
        args1.transportFactory(new TFastFramedTransport.Factory());
        args1.protocolFactory(new TJSONProtocol.Factory());
        args1.getExecutorService();
        args1.processor(new HelloService.Processor(new HelloServiceImpl()));

        TServer server = new TThreadedSelectorServer(args1);
        System.out.println("启动成功");
        server.serve();

    }
}
