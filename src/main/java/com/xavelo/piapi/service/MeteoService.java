package com.xavelo.piapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeteoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeteoService.class);

    private static final String PI_METEO_KAFKA_TOPIC = "pi-meteo-topic";

    @Autowired
    private KafkaService kafkaService;

    public void meteoActivity(String message) {
        LOGGER.info("-> meteoActivity: {}", message);
        kafkaService.sendMessage(PI_METEO_KAFKA_TOPIC, message);
    }

}
