package com.redis.cache.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.StringUtils;

@EnableCaching
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    // Method Caching에 사용할 키 생성

    // keyGenerator 예시 - 모든 파라미터를 사용하는 KeyGenerator
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> (params.length == 0) ? "" : StringUtils.arrayToCommaDelimitedString(params);
    }

    // keyGeneratorV2 예시 - 2개 파라미터만 사용하는 KeyGenerator
    @Bean
    public KeyGenerator keyGeneratorV2() {
        return (target, method, params) -> {
            StringBuilder keyNameBilder = new StringBuilder();
            for(int i = 0; i < params.length; i++) {
                if(i < 2) {
                    keyNameBilder.append(params.toString());
                    break;
                }
            }
            return "keyGeneratorV2_" + keyNameBilder.toString();
        };
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofHours(1))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
        redisCacheConfiguration.usePrefix();

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration).build();

    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }
}
