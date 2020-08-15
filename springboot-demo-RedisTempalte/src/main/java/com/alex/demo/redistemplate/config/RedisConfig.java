package com.alex.demo.redistemplate.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @Auther: Alex
 * @Date: 2020/3/11 11:07
 * @Description:
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	/**
	 * Bean01 此配置和下面的Bean02,任选一个即可
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory, RedisTemplate redisTemplate) {
		// Duration.ofSeconds(60) 设置key失效时间 60s
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(600)).disableCachingNullValues()
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));
		return RedisCacheManager.builder(factory).cacheDefaults(config).transactionAware().build();
	}

	/**
	 * Bean02 此配置和上面的Bean01,任选一个即可
	 */
	// @Bean
	// public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
	// RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
	// RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(600))
	// .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));
	// return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
	// }

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate();
		template.setConnectionFactory(factory);

		// 设置序列化
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(getSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(getSerializer());

		template.afterPropertiesSet();
		template.setEnableTransactionSupport(true);

		return template;
	}

	private Jackson2JsonRedisSerializer getSerializer() {
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		// 以下两行是解决jackson2无法反序列化LocalDateTime的问题
		om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		om.registerModule(new JavaTimeModule());

		jackson2JsonRedisSerializer.setObjectMapper(om);
		return jackson2JsonRedisSerializer;
	}
}