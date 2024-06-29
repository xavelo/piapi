package com.xavelo.kafkaproducerk3s;

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
    private KafkaProducer kafkaProducer;

    @GetMapping("/ping")
    public String ping() {
        logger.info("ping received");
        logger.info("pong returned");
        return "pooooong";
    }

    @PostMapping("/message")
    public String sendMessage(@RequestBody Map<String, String> json) {
        String topic = json.get("topic");
        String message = json.get("message");
        logger.info("Message received for topic %S: %s", topic, message);
        kafkaProducer.sendMessage(topic, message);
        return "Message sent to Kafka: " + message;
    }

}

