package com.ydcqy.cloud.customer.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by xiaoyu on 2017/10/13.
 */
public class HttpUtil {
    public static String loginAndReturnCookie(String urlStr, String method, String reqParam) throws IOException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            setHeader(conn);
            if ("POST".equalsIgnoreCase(method)) {
                setRequestBody(conn, reqParam);
            }
            String responseBody = getResponseBody(conn);
            List<String> ss = conn.getHeaderFields().get("Set-Cookie");
            String cookies = StringUtils.join(ss, ";");
            return cookies;
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }
    }

    public static String queryInfo(String urlStr, String method, String cookie) throws IOException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Cookie", cookie);
            setHeader(conn);
            return getResponseBody(conn);
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }
    }

    private static void setHeader(HttpURLConnection conn) {
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setUseCaches(false);
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(30000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
    }

    private static void setRequestBody(HttpURLConnection conn, String reqParam) throws IOException {
        OutputStream out = conn.getOutputStream();
        out.write(reqParam.getBytes());
        out.flush();
        out.close();
    }

    private static String getResponseBody(HttpURLConnection conn) throws IOException {
        try {
            return StreamUtils.copyToString(conn.getInputStream(), Charset.defaultCharset());
        } finally {
            conn.getInputStream().close();
        }
    }

    public static void main(String[] args) throws IOException {
//        String cookie = loginAndReturnCookie("https://console.winxuan.com/employee/login", "POST", "name=xiaoyu&password=abcd1234");
//        System.out.println(cookie);
        String res = queryInfo("http://localhost:8101/dsg", "GET", "");
        System.out.println(res);
    }
}
