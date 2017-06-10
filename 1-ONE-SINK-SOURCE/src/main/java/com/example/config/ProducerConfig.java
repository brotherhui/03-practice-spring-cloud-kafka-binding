package com.example.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.System.currentTimeMillis;

@Configuration
@EnableBinding(Source.class)
public class ProducerConfig {
	
	private final static Logger logger = LoggerFactory.getLogger(ProducerConfig.class);

    @InboundChannelAdapter(Source.OUTPUT)
    public String currentTimeInMillis() {
        String greeting = "current time is: " + currentTimeMillis();

        logger.info("=============== producing: {}", greeting);
        return greeting;
    }
}