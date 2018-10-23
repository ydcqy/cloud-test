package com.ydcqy.ynet.response;

/**
 * @author xiaoyu
 */
public interface ResponseListener<T> {
    void onResponse(T response);
}
