package com.ydcqy.ycache.cluster;

/**
 * @author xiaoyu
 */
public class Node {
    private String host;
    private Integer port;

    public Node() {
    }

    public Node(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


    public int hashCode() {
        int h;
        return (h = host.hashCode()) ^ (h >> 16) ^ port;
    }

    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        Node n = (Node) obj;
        return host.equals(n.getHost()) && port.equals(n.getPort());
    }

}
