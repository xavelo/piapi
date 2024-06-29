package com.xavelo.kafkaproducerk3s;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PiApiController {

    private static final Logger logger = LoggerFactory.getLogger(PiApiController.class);

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/ping")
    public String ping() {
        logger.info("ping received");
        logger.info("pong returned");
        return "pong";
    }

    @PostMapping("/message")
    public String sendMessage(@RequestBody String message) {
        logger.info("Message received: %s", message);
        kafkaProducer.sendMessage(message);
        return "Message sent to Kafka: " + message;
    }

}

