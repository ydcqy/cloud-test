package com.ydcqy.cloud.services.top.util;

import com.alibaba.fastjson.JSON;
import org.springframework.util.Base64Utils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xiaoyu on 2017/10/13.
 */
public class ImageUtil {
    public static byte[] downloadFileData(String urlStr) throws IOException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(30000);
            return StreamUtils.copyToByteArray(conn.getInputStream());
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }
    }

    public static String encodeToString(byte[] bytes) {
        return Base64Utils.encodeToString(bytes);
    }

}
