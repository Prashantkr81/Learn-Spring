package com.prashant.urlshortener.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class AppProperties {

    @Value("${app.base-url}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    private long redisTtl = 3600;

    public long getRedisTtl() {
        return redisTtl;
    }

    public void setRedisTtl(long redisTtl) {
        this.redisTtl = redisTtl;
    }
}