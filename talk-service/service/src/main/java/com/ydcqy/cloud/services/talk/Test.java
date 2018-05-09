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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xiaoyu
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws  Exception {
//        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
//        System.out.println(Arrays.asList(resourceResolver.getResources("mapper/*")).get(0).getURL());
Object obj=new Object();
new Thread(){
    @Override
    public void run() {

        System.out.println(1111);
        try {
            synchronized (obj) {

                Thread.sleep(2000);
                System.out.println(1111);
            }
            } catch(InterruptedException e){
                e.printStackTrace();
            }

    }
}.start();
    }
public void abc(boolean b){

}public void abc(Boolean b){

}
}
