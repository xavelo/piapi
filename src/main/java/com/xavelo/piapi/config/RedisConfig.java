package com.xavelo.piapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        logger.info("connectionFactory ->" + connectionFactory);
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        try {
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
            redisTemplate.setConnectionFactory(connectionFactory);
        } catch (Exception e) {
            logger.error("Error getting Redis Template connection ", e);
        }
        return redisTemplate;
    }

}
