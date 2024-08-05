package com.xavelo.piapi.controller;

import com.xavelo.piapi.service.KafkaService;
import com.xavelo.piapi.service.LedService;
import com.xavelo.piapi.service.MeteoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PiApiController {

    private static final Logger logger = LoggerFactory.getLogger(PiApiController.class);

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private LedService ledService;

    @Autowired
    private MeteoService meteoService;

    @GetMapping("/ping")
    public String ping() {
        logger.info("ping received");
        logger.info("pong returned");
        return "pooooooooooooooooooooooooooooooooooooooong";
    }

    @PostMapping("/message")
    public String sendMessage(@RequestBody Map<String, String> json) {
        String topic = json.get("topic");
        String message = json.get("message");        
        kafkaService.sendMessage(topic, message);
        return "Message sent to Kafka: " + message;
    }

    @PostMapping("/meteo/message")
    public String meteoMessage(@RequestBody Map<String, String> json) {
        String message = json.get("message");
        meteoService.meteoActivity(message);
        return "Message sent to Kafka: " + message;
    }

    @PostMapping("/led/message")
    public String led(@RequestBody Map<String, String> json) {
        String message = json.get("message");
        ledService.ledActivity(message);
        return "Led message sent to Kafka: " + message;
    }

}

