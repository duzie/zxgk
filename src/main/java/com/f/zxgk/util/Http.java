package com.f.zxgk.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;

import javax.net.ssl.SSLContext;

@Slf4j
public class Http {

    private static HttpClientConnectionManager cm;

    static {
        try {
            final SSLContext sslcontext = SSLContexts.custom()
                    .loadTrustMaterial((chain, authType) -> true)
                    .build();
            final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                    .setSslContext(sslcontext)
                    .build();
            cm = PoolingHttpClientConnectionManagerBuilder.create()
                    .setSSLSocketFactory(sslSocketFactory)
                    .build();
        } catch (Exception e) {
            log.error("初始化失败", e);
        }
    }

    public static final CloseableHttpClient NO_TRUESTED_CLIENT = HttpClients.custom().setConnectionManager(cm).build();

}
