package com.example.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;
/**
 * When using @EnableBinding(Source.class), Spring Cloud Stream automatically creates a message channel with the name output which is used by the @InboundChannelAdapter.
 */

@Configuration
@EnableBinding(Source.class)
public class ProducerConfig {
	
	private final static Logger logger = LoggerFactory.getLogger(ProducerConfig.class);


    /** Produce one event every 10 seconds with a Poller. */
    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
    public MessageSource<TimedMessage> timerMessageSource() {
        return () -> MessageBuilder.withPayload(new TimedMessage(new Date().getTime()+"", "Kafka")).build();
    }

    private static class TimedMessage {

        private String time;
        private String message;

        public TimedMessage(String time, String message) {
            this.time = time;
            this.message = message;
        }

        public String getTime() {
            return time;
        }

        public String getMessage() {
            return this.message;
        }
    }

}