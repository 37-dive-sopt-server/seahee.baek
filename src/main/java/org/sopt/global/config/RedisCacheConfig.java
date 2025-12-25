package org.sopt.global.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@EnableCaching
public class RedisCacheConfig {

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory factory) {

		// 단일 아티클 상세 조회
		RedisCacheConfiguration articleDetailCache =
			RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(5))
				.disableCachingNullValues();

		// 전체 아티클 조회
		RedisCacheConfiguration articleCursorCache =
			RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(30))
				.disableCachingNullValues();

		// 검색 조회
		RedisCacheConfiguration articleSearchCache =
			RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(20))
				.disableCachingNullValues();

		return RedisCacheManager.builder(factory)
			.withCacheConfiguration("article:detail", articleDetailCache)
			.withCacheConfiguration("article:cursor", articleCursorCache)
			.withCacheConfiguration("article:search", articleSearchCache)
			.build();
	}
}