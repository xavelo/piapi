package com.xavelo.piapi;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ReadinessStateChangeListener implements ApplicationListener<AvailabilityChangeEvent<ReadinessState>> {
    private static final Logger logger = LoggerFactory.getLogger(ReadinessStateChangeListener.class);

    @Override
    public void onApplicationEvent(AvailabilityChangeEvent<ReadinessState> event) {
        logger.info("Readiness state changed to {}. Source: {}",
                event.getState(), event.getSource());

        if (event.getState() == ReadinessState.REFUSING_TRAFFIC) {
            logger.info("Application is now refusing traffic. Stack trace:", new Exception("Stack trace"));
        }
    }
}
