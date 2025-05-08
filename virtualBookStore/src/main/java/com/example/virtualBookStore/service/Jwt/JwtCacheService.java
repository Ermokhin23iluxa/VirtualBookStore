package com.example.virtualBookStore.service.Jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtCacheService {

    private final CacheManager cacheManager;

    @Value(value = "${cache.access-token.name}")
    private String accessTokenCacheName;

    @Value(value = "${cache.refresh-token.name}")
    private String refreshTokenCacheName;

    public void putAccessToken(String email, String accessToken) {
        Cache accessTokenCache = cacheManager.getCache(accessTokenCacheName);
        if (accessTokenCache != null) {
            accessTokenCache.put(email, accessToken);
        }
    }

    public void putRefreshToken(String email, String refreshToken) {
        Cache refreshTokenCache = cacheManager.getCache(refreshTokenCacheName);
        if (refreshTokenCache != null) {
            refreshTokenCache.put(email, refreshToken);
        }
    }

    public String getAccessToken(String email) {
        Cache accessTokenCache = cacheManager.getCache(accessTokenCacheName);
        return accessTokenCache==null ? null : accessTokenCache.get(email, String.class);
    }

    public String getRefreshToken(String email) {
        Cache refreshTokenCache = cacheManager.getCache(refreshTokenCacheName);
        return refreshTokenCache==null ? null : refreshTokenCache.get(email, String.class);
    }
}
