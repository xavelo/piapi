package com.xavelo.piapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.time.LocalTime.now;

@Service
public class LedService {

    private static final Logger logger = LoggerFactory.getLogger(LedService.class);

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private RedisService redisService;

    public void ledActivity(String message) {
        logger.info("-> ledActivity: " + message);
        kafkaService.sendMessage("pi-topic", message);
        logger.info("-> calling redisService...");
        redisService.saveData(now().toString(), message);
    }

}
