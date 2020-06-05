package com.redis.cache.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.cache.service.CacheService;

@RestController
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @GetMapping("/cache1")
    public String cacheServiceTest(@RequestParam String key) {
        return cacheService.cacheableTest(key);
    }

    @GetMapping("/cache2")
    public String cacheServiceTest(@RequestParam String key, @RequestParam String key2) {
        Map<String, Object> params = new HashMap<>();
        params.put("one", key);
        params.put("two", key2);
        return cacheService.cacheableTest(params);
    }

    @GetMapping("/cache3")
    public String cacheServiceTest(@RequestParam String key, @RequestParam String key2, @RequestParam String key3) {
        Map<String, Object> params = new HashMap<>();
        params.put("one", key);
        params.put("two", key2);
        return cacheService.cacheableTest(params, key3);
    }

    @GetMapping("/cache:clean")
    public String cacheableCleanAllTest() {
        return cacheService.cacheableCleanAllTest();
    }
}
