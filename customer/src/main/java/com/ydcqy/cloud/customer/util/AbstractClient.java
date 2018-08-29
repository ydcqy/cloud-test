//package com.ydcqy.cloud.customer.util;
//
//import com.alibaba.fastjson.JSON;
//
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.charset.Charset;
//import java.util.zip.GZIPInputStream;
//
///**
// * @author xiaoyu
// */
//public abstract class AbstractClient implements Client {
//    private static final String METHOD_GET  = "GET";
//    private static final String METHOD_POST = "POST";
//    protected String urlStr;
//    protected String token;
//
//    public void setUrl(String url) {
//        this.urlStr = url;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    private void check() {
//        if (null == urlStr) {
//            throw new IllegalArgumentException("url不能为空");
//        }
//        if (null == token) {
//            throw new IllegalArgumentException("token不能为空");
//        }
//    }
//
//    @Override
//    public <T extends Response> T send(Request<T> request) throws YdyException {
//        check();
//        HttpURLConnection conn = null;
//        try {
//            StringBuilder getBuilder = new StringBuilder();
//            getBuilder.append(urlStr)
//                    .append("?")
//                    .append("token=")
//                    .append(token);
//            if (METHOD_GET.equalsIgnoreCase(request.getMethod())) {
//                getBuilder.append("&json=")
//                        .append(URLEncoder.encode(request.getParams(), "utf-8"));
//            }
//            URL url = new URL(getBuilder.toString());
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod(request.getMethod());
//            setHeader(conn);
//            if (METHOD_POST.equalsIgnoreCase(request.getMethod())) {
//                setRequestBody(conn, request.getParams());
//            }
//            String responseBody = getResponseBody(conn);
//            Class<? extends Response> responseClass = request.getResponseClass();
//            Response response = parseBody(responseBody, responseClass);
//            return (T) response;
//        } catch (Exception e) {
//            throw new YdyException(e);
//        } finally {
//            if (null != conn) {
//                conn.disconnect();
//            }
//        }
//    }
//
//    private <T extends Response> T parseBody(String body, Class<? extends Response> clazz) {
//        try {
//            Response response = JSON.parseObject(body, clazz);
//            response.setBody(body);
//            return (T) response;
//        } catch (Exception e) {
//            throw new IllegalStateException("服务端响应数据格式不对，请联系相关人员");
//        }
//    }
//
//    private void setRequestBody(HttpURLConnection conn, String reqParam) throws IOException {
//        OutputStream out = conn.getOutputStream();
//        out.write(reqParam.getBytes());
//        out.flush();
//        out.close();
//    }
//
//    private String getResponseBody(HttpURLConnection conn) throws IOException {
//        try {
//            InputStream input = conn.getInputStream();
//            String ce = conn.getHeaderField("Content-Encoding");
//            if (ce != null && ce.equalsIgnoreCase("gzip")) {
//                input = new GZIPInputStream(input);
//            }
//            return copyToString(input, Charset.defaultCharset());
//        } finally {
//            conn.getInputStream().close();
//        }
//    }
//
//    private String copyToString(InputStream in, Charset charset) throws IOException {
//        if (in == null) {
//            return "";
//        }
//        StringBuilder out = new StringBuilder();
//        InputStreamReader reader = new InputStreamReader(in, charset);
//        char[] buffer = new char[4096];
//        int bytesRead;
//        while ((bytesRead = reader.read(buffer)) != -1) {
//            out.append(buffer, 0, bytesRead);
//        }
//        return out.toString();
//    }
//
//    private void setHeader(HttpURLConnection conn) {
//        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
////        /*conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");*/
//        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setUseCaches(false);
//        conn.setConnectTimeout(30000);
//        conn.setReadTimeout(30000);
//        conn.setDoOutput(true);
//        conn.setDoInput(true);
//    }
//}
