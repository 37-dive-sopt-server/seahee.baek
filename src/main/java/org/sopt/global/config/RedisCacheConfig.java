package org.sopt.global.config;

import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@EnableCaching
public class RedisCacheConfig {

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory factory) {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		GenericJackson2JsonRedisSerializer jsonSerializer =
			new GenericJackson2JsonRedisSerializer(objectMapper);

		RedisCacheConfiguration defaultConfig =
			RedisCacheConfiguration.defaultCacheConfig()
				.serializeValuesWith(
					RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer)
				)
				.disableCachingNullValues();

		// 단일 아티클 상세 조회
		RedisCacheConfiguration articleDetailCache =
			defaultConfig.entryTtl(Duration.ofMinutes(5));

		// 커서 기반 전체 조회
		RedisCacheConfiguration articleCursorCache =
			defaultConfig.entryTtl(Duration.ofSeconds(30));

		// 검색 조회
		RedisCacheConfiguration articleSearchCache =
			defaultConfig.entryTtl(Duration.ofSeconds(20));

		return RedisCacheManager.builder(factory)
			.cacheDefaults(defaultConfig)
			.withCacheConfiguration("article:detail", articleDetailCache)
			.withCacheConfiguration("article:all", articleCursorCache)
			.withCacheConfiguration("article:search", articleSearchCache)
			.build();
	}
}