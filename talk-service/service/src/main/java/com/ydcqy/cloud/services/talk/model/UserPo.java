package com.ydcqy.cloud.services.talk.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoyu
 */
@Data
public class UserPo implements Serializable {
    private static final long serialVersionUID = -1965933709992657238L;
    private Integer id;
    private String username;
}
