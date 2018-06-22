package com.ydcqy.cloud.server;


import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author xiaoyu
 */
@Slf4j
public class Test {

    public static void main(String[] args) {

        ByteBuffer wrap = ByteBuffer.allocate(16);
        wrap.putInt(0xff);
        System.out.println(wrap.getInt());

        HashMap map = new HashMap();
        map.put("addd", new Object());
        map.put("ad1dd", new Object());
        map.put("ad3dd", new Object());
        map.put("ad4dd", new Object());
        map.put("ad5dd", new Object());
        map.put("ad6dd", new Object());
        map.put("ad3x1dd", new Object());
        map.put("a3ddd", new Object());
        map.put("a23ddd", new Object());
        map.put("a2sa33ddd", new Object());
        map.put("adsafdd", new Object());
        map.put("adzdd", new Object());
        map.put("asdxdd", new Object());
        map.put("adfsdgdd", new Object());
        map.put("afdsgddd", new Object());
        map.put("ad33ddd", new Object());
        map.put(null, "abc");
        System.out.println(map.get(null));

        Set set = map.entrySet();
        System.out.println(new String("abc").hashCode());
        System.out.println(new String("abc").hashCode());
        System.out.println((int) 'a');
        TreeMap m = new TreeMap();
        m.put("abc", "");


    }

}
