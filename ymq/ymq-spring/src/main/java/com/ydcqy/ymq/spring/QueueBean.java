package com.ydcqy.ymq.spring;

import com.ydcqy.ymq.message.QueueType;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author xiaoyu
 */
public class QueueBean implements InitializingBean {
    private String   name;
    private String   type;
    private String   interfaceName;
    private Class<?> interfaceClass;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Class<?> getInterface() {
        return interfaceClass;
    }

    public void setInterface(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public void afterPropertiesSet() throws Exception {

    }

    public QueueType getQueueType() {
        QueueType[] values = QueueType.values();
        for (QueueType value : values) {
            if (value.toString().equalsIgnoreCase(type))
                return value;
        }
        return null;
    }
}
