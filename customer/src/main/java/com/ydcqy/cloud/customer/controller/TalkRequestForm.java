package com.ydcqy.cloud.customer.controller;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by lenovo on 2018/1/22.
 */
@Data
public class TalkRequestForm {
    private String talkerName;
    private String targetName;
    private Date time;
}
