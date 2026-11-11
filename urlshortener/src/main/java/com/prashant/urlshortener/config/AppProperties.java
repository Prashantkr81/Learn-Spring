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

    private Duration redisTtl;
    @Value("${app.redis.ttl}")
    public Duration getRedisTtl(){
        return redisTtl;
    }
}