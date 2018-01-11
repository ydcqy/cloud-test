package com.ydcqy.cloud.server;

import com.ydcqy.cloud.services.talk.TalkServiceMain;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TalkServiceMain.class)
public class ServerApplicationTests {

    @Test
    public void contextLoads() {
        log.info("contextLoads");
    }

}
