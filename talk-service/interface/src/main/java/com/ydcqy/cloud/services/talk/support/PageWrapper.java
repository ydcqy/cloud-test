package com.ydcqy.cloud.services.talk.support;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaoyu
 */
@Data
public class PageWrapper<T> implements Serializable {
    private static final long serialVersionUID = -8253960997635941956L;
    private int pageNum;
    private int pageSize;
    private long total;
    private List<T> results;
}
