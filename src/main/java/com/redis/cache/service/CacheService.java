package com.redis.cache.service;

import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Cacheable(value = "cacheName1", keyGenerator = "keyGenerator")
    public String cacheableTest(String normalKey) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello world :: " + normalKey;
    }

    @Cacheable(value = "cacheName2", keyGenerator = "keyGeneratorV2")
    public String cacheableTest(Map<String, Object> params) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello world :: " + params.toString();
    }

    @Cacheable(value = "cacheName3", keyGenerator = "keyGenerator")
    public String cacheableTest(Map<String, Object> params, String normalKey) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello world :: " + normalKey + " :: " + params.toString();
    }

    @CacheEvict(value = "cacheName4")
    public String cacheableCleanAllTest() {
        return "ok";
    }
}
