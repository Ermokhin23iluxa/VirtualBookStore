package com.example.virtualBookStore.configuration;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    // Локальный ObjectMapper для Redis (НЕ @Bean чтобы не переопределять глобальный)
    private ObjectMapper createRedisObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean
    @Primary
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        GenericJackson2JsonRedisSerializer jacksonSerializer =
                new GenericJackson2JsonRedisSerializer(createRedisObjectMapper());

        RedisCacheConfiguration baseConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jacksonSerializer))
                .disableCachingNullValues();

        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();
        cacheConfigs.put(accessTokenCacheName, baseConfig.entryTtl(accessTokenTtl));
        cacheConfigs.put(refreshTokenCacheName, baseConfig.entryTtl(refreshTokenTtl));
        cacheConfigs.put("books", baseConfig.entryTtl(Duration.ofHours(2)));
        cacheConfigs.put("categories", baseConfig.entryTtl(Duration.ofHours(2)));

        // Построим менеджер и явно укажем initialCacheNames
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(baseConfig)
                .withInitialCacheConfigurations(cacheConfigs)
                .initialCacheNames(Set.copyOf(cacheConfigs.keySet()))
                .transactionAware();

        return builder.build();
    }
}
