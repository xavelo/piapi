package com.xavelo.piapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        logger.info("---> topic '" + message + "' --- message '" + topic + "'");
        kafkaTemplate.send(topic, message); 
    }

    public void sendAsynchMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("pi-topic", message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.error("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                logger.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }

}
