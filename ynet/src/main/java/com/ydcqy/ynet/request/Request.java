package com.ydcqy.ynet.request;

import com.ydcqy.ynet.response.Response;

/**
 * @author xiaoyu
 */
public interface Request<T extends Response> {
    Class<T> getResponseClass();
}
