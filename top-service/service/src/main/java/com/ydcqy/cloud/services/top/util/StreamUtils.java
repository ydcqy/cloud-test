package com.ydcqy.cloud.services.top.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by xiaoyu on 2017/11/3.
 */
public class StreamUtils {
    public static List<String> readToStringList(InputStream input) throws IOException {
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader br = new BufferedReader(isr);
        List<String> ls = new ArrayList<String>();
        String temp;
        while (null != (temp = br.readLine())) {
            ls.add(temp);
        }
        isr.close();
        return ls;
    }

    public static List<Integer> readToIntegerList(InputStream input) throws IOException {
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader br = new BufferedReader(isr);
        List<Integer> ls = new ArrayList<Integer>();
        String temp;
        while (null != (temp = br.readLine())) {
            ls.add(Integer.valueOf(temp));
        }
        isr.close();
        return ls;
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString());
    }
}
