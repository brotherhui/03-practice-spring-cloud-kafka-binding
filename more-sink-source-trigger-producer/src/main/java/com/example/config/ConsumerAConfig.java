package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;

import com.example.data.Data;
import com.example.data.MultiSink;


/**
 * Configures Spring Cloud Stream support.
 *
 * See http://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/
 * for more information.
 */

@Configuration
@EnableBinding(MultiSink.class)
public class ConsumerAConfig {


	private static Logger logger = LoggerFactory.getLogger(ConsumerAConfig.class);
	
	/*
	 * Requirement: receive the message from topic queue
	 */
	@StreamListener(MultiSink.SINK1)
	public void listen(Data sinkData) {
		
		logger.info("Received at MultiSink: " + sinkData.toString());
		
	}
	

}