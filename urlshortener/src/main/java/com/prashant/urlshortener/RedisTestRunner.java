package com.prashant.urlshortener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTestRunner implements CommandLineRunner {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisTestRunner(
            RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run(String... args) {

        redisTemplate.opsForValue()
                .set("test:key", "Hello Redis");

        String value = redisTemplate.opsForValue()
                .get("test:key");

        System.out.println("Redis Response: " + value);
    }
}