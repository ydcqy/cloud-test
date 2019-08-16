package com.ydcqy.cloud.services.talk.model;

import lombok.Data;

import java.util.Date;

/**
 * @author xiaoyu
 */
@Data
public class UserOperationLogPo {
    /**
     * 多少个
     */
    private Long id;
    private Long user;
    private String ip;
    private String devices;
    private short operationType;
    private Date createTime;

}
