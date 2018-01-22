package com.ydcqy.cloud.customer.support;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2018/1/23.
 */
@Slf4j
public class DateFormatConvert implements Converter<String, Date> {
    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date convert(String s) {
        log.info("转换时间:{}", s);
        if (StringUtils.isBlank(s)) {
            return null;
        }
        try {
            if (s.matches("\\d+")) {
                return new Date(Long.parseLong(s));
            }
            if (s.contains("-")) {
                if (s.contains(":")) {
                    return sdf2.parse(s);
                } else {
                    return sdf1.parse(s);
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException("时间转换出错", e);
        }
        throw new RuntimeException(String.format("无法转换时间：%s", s));
    }

}
