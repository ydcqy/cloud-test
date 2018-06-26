package com.ydcqy.ycache.cluster.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.net.URI;

/**
 * @author xiaoyu
 */
public class ClusterJedisPool extends JedisPool {
    public ClusterJedisPool() {
        super();
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host) {
        super(poolConfig, host);
    }

    public ClusterJedisPool(String host, int port) {
        super(host, port);
    }

    public ClusterJedisPool(String host) {
        super(host);
    }

    public ClusterJedisPool(String host, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(host, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public ClusterJedisPool(URI uri) {
        super(uri);
    }

    public ClusterJedisPool(URI uri, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(uri, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public ClusterJedisPool(URI uri, int timeout) {
        super(uri, timeout);
    }

    public ClusterJedisPool(URI uri, int timeout, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(uri, timeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password) {
        super(poolConfig, host, port, timeout, password);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password, boolean ssl) {
        super(poolConfig, host, port, timeout, password, ssl);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(poolConfig, host, port, timeout, password, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port) {
        super(poolConfig, host, port);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, boolean ssl) {
        super(poolConfig, host, port, ssl);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(poolConfig, host, port, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout) {
        super(poolConfig, host, port, timeout);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, boolean ssl) {
        super(poolConfig, host, port, timeout, ssl);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(poolConfig, host, port, timeout, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password, int database) {
        super(poolConfig, host, port, timeout, password, database);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password, int database, boolean ssl) {
        super(poolConfig, host, port, timeout, password, database, ssl);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password, int database, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(poolConfig, host, port, timeout, password, database, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password, int database, String clientName) {
        super(poolConfig, host, port, timeout, password, database, clientName);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password, int database, String clientName, boolean ssl) {
        super(poolConfig, host, port, timeout, password, database, clientName, ssl);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password, int database, String clientName, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(poolConfig, host, port, timeout, password, database, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, String host, int port, int connectionTimeout, int soTimeout, String password, int database, String clientName, boolean ssl, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(poolConfig, host, port, connectionTimeout, soTimeout, password, database, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, URI uri) {
        super(poolConfig, uri);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, URI uri, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(poolConfig, uri, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, URI uri, int timeout) {
        super(poolConfig, uri, timeout);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, URI uri, int timeout, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(poolConfig, uri, timeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, URI uri, int connectionTimeout, int soTimeout) {
        super(poolConfig, uri, connectionTimeout, soTimeout);
    }

    public ClusterJedisPool(GenericObjectPoolConfig poolConfig, URI uri, int connectionTimeout, int soTimeout, SSLSocketFactory sslSocketFactory, SSLParameters sslParameters, HostnameVerifier hostnameVerifier) {
        super(poolConfig, uri, connectionTimeout, soTimeout, sslSocketFactory, sslParameters, hostnameVerifier);
    }

    @Override
    public Jedis getResource() {
        return super.getResource();
    }

    @Override
    public void returnBrokenResource(Jedis resource) {
        super.returnBrokenResource(resource);
    }

    @Override
    public void returnResource(Jedis resource) {
        super.returnResource(resource);
    }
}
