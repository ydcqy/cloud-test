package com.ydcqy.ymq.util;

/**
 * @author xiaoyu
 */
public class StringUtils {
    /**
     * 将字符串转换成unicode码
     *
     * @param str 要转换的目标字符串
     * @return
     */
    public static String stringToUnicode(String str) {
        char[] cs = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char c : cs) {
            int len = Integer.toHexString(c).length();
            builder.append("\\u");
            for (int i = 0; i < 4 - len; i++) {
                builder.append("0");
            }
            builder.append(Integer.toHexString(c));
        }
        return builder.toString();
    }

    /**
     * 将unicode转换成字符串
     *
     * @param unicodeStr 要转换的目标字符串
     * @return
     */
    public static String unicodeToString(String unicodeStr) {
        String[] strs = unicodeStr.split("\\\\u");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < 4 || i == 0) {
                builder.append(strs[i]);
            } else if (strs[i].length() == 4) {
                builder.append((char) Integer.parseInt(strs[i], 16));
            } else {
                builder.append((char) Integer.parseInt(strs[i].substring(0, 4), 16));
                builder.append(strs[i].substring(4));
            }
        }
        return builder.toString();
    }

    /**
     * 按字节大小截取字符串
     *
     * @param targetStr 要截取的目标字符串
     * @param len       要截取的字节大小
     * @return
     * @throws Exception
     */
    public static String substring(String targetStr, int len) throws Exception {
        String cst = "utf-8";
        if (targetStr.getBytes(cst).length <= len) {
            return targetStr;
        }
        char[] cs = targetStr.toCharArray();
        int sum = 0;
        String s;
        StringBuilder builder = new StringBuilder();
        for (char c : cs) {
            s = c + "";
            sum += s.getBytes(cst).length;
            if (sum > len) {
                break;
            }
            builder.append(s);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = unicodeToString("{\"content\":\"wxid_359khkmt9xen22:\\\\n\\u5927\\u8bfe\\u7834\\u96f6\\ue312\\uff0c\\u5927\\u8bfe\\u8de8\\u661f\\ue312\\uff0c\\u6b63\\u5f53\\u65f6\\ue10d\\\\n\\u5927\\u8bfe\\u7834\\u96f6\\ue312\\uff0c\\u5927\\u8bfe\\u8de8\\u661f\\ue312\\uff0c\\u6b63\\u5f53\\u65f6\\ue10d\\\\n[\\u7ea2\\u5305]\\u3010\\u56e2\\u961f\\u4eca\\u65e550\\u5355\\u5012\\u8ba1\\u65f6[\\u8272]\\u3011\\\\n[\\u7ea2\\u5305]\\u3010\\u56e2\\u961f\\u4eca\\u65e550\\u5355\\u5012\\u8ba1\\u65f6[\\u8272]\\u3011\\\\n[\\u62e5\\u62b1]\\u7b2c\\u4e00\\u5355\\uff1a\\u5510\\u4e3d\\u4e3d\\ue231\\u7834\\u96f6\\u5566\\\\n[\\u62e5\\u62b1]\\u7b2c\\u4e8c\\u5355\\uff1a\\u738b\\u4f73\\u670b\\ue231\\u65b0\\u4eba\\u7834\\u96f6\\u5566\\uff01\\\\n[\\u62e5\\u62b1]\\u7b2c\\u4e09\\u5355\\uff1a\\u8d3e\\u65ed\\u838e\\ue231\\u7834\\u96f6\\u5566\\uff01\\\\n[\\u62e5\\u62b1]\\u7b2c\\u56db\\u5355\\uff1a\\u9ad8\\u57f9\\u6cfd\\ue231\\u7834\\u96f6\\u5566\\uff01\\\\n[\\u62e5\\u62b1]\\u7b2c\\u4e94\\u5355\\uff1a\\u5c48\\u9756\\u73ae\\ue132\\\\n[\\u62e5\\u62b1]\\u7b2c\\u516d\\u5355\\uff1a\\u738b\\u4f73\\u670b\\ue231\\u65b0\\u4eba\\u7b2c\\u4e8c\\u5355\\uff01\\\\n[\\u62e5\\u62b1]\\u7b2c\\u4e03\\u5355\\uff1a\\u5f20   \\u68a6\\ue231\\u7834\\u96f6\\u5566\\uff01\\\\n[\\u62e5\\u62b1]\\u7b2c\\u516b\\u5355\\uff1a\\u5b8b\\u6587\\u9f99\\ue231\\u7834\\u96f6\\u5566\\uff01\\\\n[\\u62e5\\u62b1]\\u7b2c\\u4e5d\\u5355\\uff1a\\u5510\\u4e3d\\u4e3d\\ue231\\u8ba2\\u91d1\\u4e00\\u679a\\\\n[\\u62e5\\u62b1]\\u7b2c\\u5341\\u5355\\uff1a\\u5f20\\u8389\\u8363\\ue132\\\\n[\\u62e5\\u62b1]\\u7b2c11\\u5355\\uff1a\\u6bb5\\u660e\\u6d9b\\ue231\\u7834\\u96f6\\u5566\\uff01\\\\n[\\u62e5\\u62b1]\\u7b2c12\\u5355\\uff1a\\u5f20\\u80dc\\u9f99\\ue132\\\\n[\\u62e5\\u62b1]\\u7b2c13\\u5355 : \\u6731\\u601d\\u7426\\ue132\\\\n[\\u62e5\\u62b1]\\u7b2c14\\u5355 : \\u5f20 \\u68a6\\ue132\\ue132\\\\n[\\u62e5\\u62b1]\\u7b2c15\\u5355 : \\u5434\\u7389\\u7965\\ue132\\\\n[\\u62e5\\u62b1]\\u7b2c16\\u5355 : \\u7530\\u6cfd\\u5f3a\\ue132\\\\n[\\u62e5\\u62b1]\\u7b2c17\\u5355 : \\u7530\\u6cfd\\u5f3a\\ue132\\ue132\\\\n[\\u62e5\\u62b1]\\u7b2c18\\u5355 : \\u8d75\\u6653\\u707f\\ue231 \\u7834\\u96f6\\u5566\\uff01\\\\n[\\u62e5\\u62b1]\\u7b2c19\\u5355 : \\u5b8b\\u6797\\u51e4\\ue231\\u7834\\u96f6\\u5566\\uff01\\\\n[\\u62e5\\u62b1]\\u7b2c20\\u5355\\uff1a\\u8d3e\\u7855\\ue231\\u7834\\u96f6\\u5566\\uff01\\\\n[\\u62e5\\u62b1]\\u7b2c21\\u5355 : \\u5f20\\u68a6\\ue132\\ue132\\ue132\\\\n[\\u62e5\\u62b1]\\u7b2c22\\u5355 : \\u5b8b\\u6587\\u9f99\\ue132\\ue132\\\\n[\\u62e5\\u62b1]\\u7b2c23\\u5355 : \\u5b59\\u5fd7\\u521a\\ue231\\u8ba2\\u91d1\\u4e00\\u679a\\\\n[\\u62e5\\u62b1]\\u7b2c24\\u5355 : \\u738b\\u68a6\\u9896\\ue231\\u7834\\u96f6\\u5566\\uff01\\\\n[\\u62e5\\u62b1]\\u7b2c25\\u5355 : \\u738b\\u4f73\\u670b\\ue231\\u65b0\\u4eba\\ue132\\ue132\\ue132\\\\n[\\u62e5\\u62b1]\\u7b2c26\\u5355  :\\\\n[\\u62e5\\u62b1]\\u7b2c27\\u5355  :\\\\n[\\u62e5\\u62b1]\\u7b2c28\\u5355  :\\\\n[\\u62e5\\u62b1]\\u7b2c29\\u5355  :\\\\n[\\u62e5\\u62b1]\\u7b2c30\\u5355  :\\\\n\\ue14c\\u4e0d\\u7834\\u4e0d\\u7acb\\uff0c\\u4eca\\u5929\\u7834\\u4e86\\uff01\\u8fd9\\u4e2a\\u6708\\u5c31\\u987a\\u5566\\ue14c\\\\n[\\u793c\\u7269]\\u4e8c\\u7ec4\\u6709\\u5c0f\\u4f19\\u4f34\\u5168\\u529b\\u51b2\\u51fb\\u5c0f\\u7ec4\\u76ee\\u6807\\uff01\\\\n[\\u793c\\u7269]\\u7ad6\\u8d77\\u4f60\\u7684\\u5927\\u65d7\\ue132\\uff0c\\u7559\\u4e0b\\u4f60\\u7684\\u5927\\u540d\\ue10e\\uff01\\uff01\\\\n\\u4e00\\u5bb6\\u4eba\\ue312\\u3001\\u4e00\\u6761\\u5fc3\\ue312\\u3001\\u4e00\\u8d77\\u5e72\\ue312\\u3001\\u4e00\\u5757\\u62fc\\ue312\\\\n\\u5168\\u529b\\u4ee5\\u8d74\\u6740\\u6740\\u6740\\ue310\\ue310\\ue310\\uff01\\uff01\\uff01\\uff01\\uff01\\uff01\\uff01\\uff01\\uff01\\uff01\",\"createTime\":\"1536220115000\",\"dataType\":\"text\",\"isSend\":\"0\",\"msgId\":\"10334\",\"nickName\":\"\\u6e90\\u8fbe\\u90d1\\u536b\\u8d85\",\"talker\":\"2773731089@chatroom\",\"userName\":\"wxid_wm8i6r82a7di12\"}");
        System.out.println(stringToUnicode(s));
    }
}