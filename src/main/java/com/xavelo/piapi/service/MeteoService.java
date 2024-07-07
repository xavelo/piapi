package com.xavelo.piapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.time.LocalTime.now;

@Service
public class MeteoService {

    private static final Logger logger = LoggerFactory.getLogger(MeteoService.class);

    private static final String PI_METEO_KAFKA_TOPIC = "pi-meteo-topic";

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private RedisService redisService;

    public void meteoActivity(String message) {
        logger.info("-> meteoActivity: " + message);
        kafkaService.sendMessage(PI_METEO_KAFKA_TOPIC, message);
        //redisService.saveData(now().toString(), message);
    }

}
