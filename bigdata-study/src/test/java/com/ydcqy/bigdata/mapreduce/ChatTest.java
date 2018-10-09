package com.ydcqy.bigdata.mapreduce;

import org.apache.hadoop.io.Text;
import org.junit.Test;

/**
 * @author xiaoyu
 */
public class ChatTest {

    @Test
    public void chatTest() {
        Text text = new Text("{\"api\":\"/add\",\"nodeContent\":\"{\\\"content\\\":\\\"wxid_12svr6ybumms22:\\\\\\\\n今天有直播间？\\\",\\\"createTime\\\":\\\"1538206113000\\\",\\\"dataType\\\":\\\"text\\\",\\\"isSend\\\":\\\"0\\\",\\\"msgId\\\":\\\"716\\\",\\\"nickName\\\":\\\"源达_石壮壮\\\",\\\"talker\\\":\\\"8614076852@chatroom\\\",\\\"userName\\\":\\\"wxid_1zcjsj2vez522\\\"}\",\"nodePath\":\"/wechat/2018/09/29/源达_石壮壮/716\"}");

//        new MapDriver<>()
    }
}
