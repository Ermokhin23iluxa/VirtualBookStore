package com.example.virtualBookStore.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

//запуск в docker redis
// docker run -d --name redis -p 6379:6379 redis:7

@Configuration
@EnableCaching
public class RedisConfig {
    @Value("${cache.access-token.name}")
    private String accessTokenCacheName;

    @Value("${cache.refresh-token.name}")
    private String refreshTokenCacheName;

    @Value("${spring.jwt.access.jwt.ttl}")
    private Duration accessTokenTtl;

    @Value("${spring.jwt.refresh.jwt.ttl}")
    private Duration refreshTokenTtl;

    @Bean
    @Primary
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
