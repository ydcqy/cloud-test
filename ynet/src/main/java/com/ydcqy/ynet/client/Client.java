package com.ydcqy.ynet.client;

import com.ydcqy.ynet.exception.YnetException;
import com.ydcqy.ynet.request.Request;
import com.ydcqy.ynet.response.Response;
import com.ydcqy.ynet.transport.Transport;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

/**
 * @author xiaoyu
 */
public interface Client extends Transport {

    InetSocketAddress getRemoteAddress();

    <T extends Response> T send(Request<T> request) throws YnetException;

    <T extends Response> Future<T> asyncSend(Request<T> request) throws YnetException;

}




