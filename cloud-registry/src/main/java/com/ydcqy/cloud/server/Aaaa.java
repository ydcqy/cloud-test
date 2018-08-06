package com.ydcqy.cloud.server;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaoyu
 */
@Service
public class Aaaa implements InitializingBean {

    private Bbbb bbbb;
    @Autowired
    public void setBbbb(Bbbb bbbb) {
        this.bbbb = bbbb;
        System.out.println("set bbbb");
    }

    public Aaaa() {
        System.out.println("aaaa....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet...");
    }
}
