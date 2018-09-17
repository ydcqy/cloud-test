package com.ydcqy.ynet.client;

import com.ydcqy.ynet.exception.YnetException;
import com.ydcqy.ynet.request.Request;
import com.ydcqy.ynet.response.Response;

import java.io.Closeable;

/**
 * @author xiaoyu
 */
public interface Client extends Closeable, Cloneable {
    <T extends Response> T send(Request<T> request) throws YnetException;
}
