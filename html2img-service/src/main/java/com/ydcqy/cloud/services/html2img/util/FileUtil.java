package com.ydcqy.cloud.services.html2img.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoyu
 * @version 1.0 2017年9月21日
 */
public class FileUtil {

    static Map<String, PrintWriter> pwMap = new HashMap<>();

    private static PrintWriter getPw(String path) throws IOException {
        PrintWriter pw = pwMap.get(path);
        if (null == pw) {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            pw = new PrintWriter(new FileWriter(file, true));
            pwMap.put(path, pw);
        }
        return pw;
    }

    public static void appendLine(String text, String path) throws IOException {
        PrintWriter pw = getPw(path);
        pw.println(text);
        pw.flush();
    }
}
