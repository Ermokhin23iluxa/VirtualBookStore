package com.example.virtualBookStore.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        // Указываем имена кэшей, которые используем в JwtCacheService
        return new ConcurrentMapCacheManager("accessTokens", "refreshTokens");
    }
}
