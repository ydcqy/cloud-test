package com.ydcqy.ymq.configuration;

import lombok.Data;

/**
 * @author xiaoyu
 */
@Data
public class ActiveMqConfiguration implements Configuration {
    private String brokerUrl;
    private String username;
    private String password;

}
