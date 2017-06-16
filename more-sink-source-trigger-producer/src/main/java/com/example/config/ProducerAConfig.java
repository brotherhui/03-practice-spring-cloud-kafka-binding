package com.example.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

import com.example.data.ASource;
/**
 * When using @EnableBinding(Source.class), Spring Cloud Stream automatically creates a message channel with the name output which is used by the @InboundChannelAdapter.
 */

@Configuration
@EnableBinding(ASource.class)
public class ProducerAConfig {
	
	private final static Logger logger = LoggerFactory.getLogger(ProducerAConfig.class);

}