package com.ydcqy.cloud.customer.controller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ydcqy.cloud.customer.support.DateDeserializer;
import lombok.Data;

import java.util.Date;

/**
 * Created by lenovo on 2018/1/22.
 */
@Data
public class TalkRequestForm {
    private String talkerName;
    private String targetName;
    @JsonDeserialize(using = DateDeserializer.class)
    private Date time;
}
