package com.ydcqy.thrift;

import com.ydcqy.thrift.service.HelloService;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiaoyu
 */
public class ClientMain {
    public static void main(String[] args) throws Exception {
        TTransport transport = new TFastFramedTransport(new TSocket("127.0.0.1", 1111));
        TProtocol protocol = new TJSONProtocol.Factory().getProtocol(transport);
        transport.open();

        HelloService.Client client = new HelloService.Client(protocol);

        System.out.println(client.sayHi("哈哈", 123));
        Thread.sleep(1000);
        System.out.println(client.sayHi("哈哈", 123));
        Thread.sleep(1000);
        System.out.println(client.sayHi("哈哈", 123));
        LockSupport.park();
    }
}
