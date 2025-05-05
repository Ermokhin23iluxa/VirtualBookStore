package com.example.virtualBookStore.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {
    @Value("${cache.access-token.name}")
    private String accessTokenCacheName;

    @Value("${cache.refresh-token.name}")
    private String refreshTokenCacheName;

    @Value("${spring.access.jwt.ttl}")
    private Duration accessTokenTtl;

    @Value("${spring.refresh.jwt.ttl}")
    private Duration refreshTokenTtl;

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .withCacheConfiguration(
                        accessTokenCacheName,
                        RedisCacheConfiguration
                                .defaultCacheConfig()
                                .entryTtl(accessTokenTtl)
                )
                .withCacheConfiguration(
                        refreshTokenCacheName,
                        RedisCacheConfiguration
                                .defaultCacheConfig()
                                .entryTtl(refreshTokenTtl)
                )
                .build();
    }

}
