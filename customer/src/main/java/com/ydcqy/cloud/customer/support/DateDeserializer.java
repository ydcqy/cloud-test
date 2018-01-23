package com.ydcqy.cloud.customer.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2018/1/24.
 */
@Slf4j
public class DateDeserializer extends JsonDeserializer<Date> {
    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String s = jsonParser.getText();
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
