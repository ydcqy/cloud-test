package com.ydcqy.ynet.ws;

import com.ydcqy.ynet.channel.Channel;
import io.netty.util.internal.StringUtil;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoyu
 */
public class WebSocketInfo {
    private Channel channel;
    private String uri;
    private Map<String, String> params;

    public Channel getChannel() {
        return channel;
    }

    void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getUri() {
        return uri;
    }

    void setUri(String uri) {
        this.uri = uri;
        params = new HashMap<>(1 << 4);
        URI u = URI.create(this.uri);
        String query = u.getQuery();
        if (StringUtil.isNullOrEmpty(query)) {
            return;
        }
        String[] qs = query.split("&");
        for (int i = 0; i < qs.length; i++) {
            String q = qs[i];
            if (!StringUtil.isNullOrEmpty(q)) {
                String[] qq = q.split("=");
                if (qq.length != 2) {
                    break;
                }
                String k = qq[0];
                String v = qq[1];
                params.put(k, v);
            }
        }
    }

    public Map<String, String> getQueryParams() {
        return params;
    }

    public String getQueryParam(String name) {
        return params.get(name);
    }

    @Override
    public String toString() {
        return "WebSocketInfo{" +
                "channel=" + channel +
                ", uri='" + uri + '\'' +
                '}';
    }
}
