package com.ydcqy.cloud.services.talk.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaoyu
 */
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 3210606575700091692L;
    private Integer id;
    private String username;
}
