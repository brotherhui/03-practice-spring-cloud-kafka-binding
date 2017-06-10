package com.example.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import com.example.data.Data;

@Configuration
@EnableBinding(ProducerAConfig.MultiSource.class)
public class ProducerAConfig {

	private static Logger logger = LoggerFactory.getLogger(ProducerAConfig.class);
	
	@Bean
	@InboundChannelAdapter(value = MultiSource.SOURCE1, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
	public MessageSource<Data> dataMessageSource() {
		logger.info("------------ producing message");
		return () -> MessageBuilder.withPayload(new Data(new Date(),"source")).build();
	}
	
	public interface MultiSource {
		//here is the very important defines, it must be same with the name in application.yml
		String SOURCE1 = "output1";

		@Output(SOURCE1)
		MessageChannel output1();
	}
}