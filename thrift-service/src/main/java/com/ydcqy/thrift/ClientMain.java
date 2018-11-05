package com.ydcqy.thrift;

import com.ydcqy.thrift.service.HelloService;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class ClientMain {
    public static void main(String[] args) throws Exception {

        TNonblockingSocket transport = new TNonblockingSocket("127.0.0.1", 1111);

        TFramedTransport tr = new TFramedTransport(transport);
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
        TProtocol protocol = protocolFactory.getProtocol(transport);


        HelloService.AsyncClient helloService = new HelloService.AsyncClient(protocolFactory, new TAsyncClientManager(), transport);

        helloService.sayHi("张三", 18, new AsyncMethodCallback<String>() {
            @Override
            public void onComplete(String response) {
                System.out.println(response);
            }

            @Override
            public void onError(Exception exception) {
                System.out.println(exception);
            }
        });
        LockSupport.park();
    }
}
