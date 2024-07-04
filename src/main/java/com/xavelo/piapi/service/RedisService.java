package com.xavelo.piapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveData(String key, Object data) {
        logger.info("-> redis save key '" + key + "' with value '" + data  + "'");
        redisTemplate.opsForValue().set(key, data);
    }

    @Cacheable("myCache")
    public Object getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
