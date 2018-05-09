package com.ydcqy.cloud.services.talk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ydcqy.cloud.services.talk.support.PageWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author xiaoyu
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        System.out.println(Arrays.asList(resourceResolver.getResources("mapper/*")).get(0).getURL());
    }
public void abc(boolean b){

}public void abc(Boolean b){

}
}
