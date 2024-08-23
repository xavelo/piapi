package com.xavelo.piapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceBak {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceBak.class);

    @Autowired
    private Environment env;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public String getRedisHostIP() {
        return env.getProperty("spring.data.redis.host");
    }

    public void saveKeyValue(String key, Object value) {
        LOGGER.info("-> redis save key '{}' with value '{}'", key, value);
        redisTemplate.opsForValue().set(key, value);
    }

    public void saveToList(String list, Object value) {
        LOGGER.info("-> redis save to list '{}' value '{}' - {}", list, value);
        redisTemplate.opsForList().leftPush(list, value.toString());
        LOGGER.info("{} list contains {} entries", list, String.valueOf(getListSize(list)));
    }

    public void resetList(String list) {
        LOGGER.info("-> redis reset list '{}'", list);
        redisTemplate.delete(list);
        LOGGER.info("{} list contains {} entries", list, String.valueOf(getListSize(list)));
    }

    @Cacheable("myCache")
    public Object getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    private long getListSize(String list) {
        return redisTemplate.opsForList().size(list);
    }

}
