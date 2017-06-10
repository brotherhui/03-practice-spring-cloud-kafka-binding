package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;

import java.math.BigInteger;

import static java.lang.System.currentTimeMillis;

@Configuration
@EnableBinding(Sink.class)
public class ConsumerConfig {

	private final static Logger logger = LoggerFactory.getLogger(ConsumerConfig.class);

	
    static BigInteger counter = BigInteger.ZERO;
    static long latest = -1;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void logMessage(String payload) {
        
        logger.info("............... {}) payload: '{}', latest: {}",
                counter = counter.add(BigInteger.ONE), payload, latest = currentTimeMillis());
    }
}
