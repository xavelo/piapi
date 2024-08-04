package com.xavelo.piapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private Environment env;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveData(String key, Object data) {
        String redisIP = env.getProperty("spring.data.redis.host");
        LOGGER.info("-> redis save key '{}' with value '{}' - {}", key, data, redisIP);
        redisTemplate.opsForValue().set(key, data);

        Object o = getData(key);
        LOGGER.info("object {}", String.valueOf(o));

    }

    @Cacheable("myCache")
    public Object getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
