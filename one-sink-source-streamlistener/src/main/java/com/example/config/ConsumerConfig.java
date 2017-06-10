package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;

import java.math.BigInteger;

import static java.lang.System.currentTimeMillis;

@Configuration
@EnableBinding(Sink.class)
public class ConsumerConfig {

	private static Logger logger = LoggerFactory.getLogger(ConsumerConfig.class);

    @StreamListener(Sink.INPUT)
    public void loggerSink(SinkTimedMessage sinkTimedMessage) {
        logger.info("Received: " + sinkTimedMessage.toString());
    }

    private static class SinkTimedMessage {

        private String time;
        private String message;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "SinkTimedMessage{" +
                    "time='" + time + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

}
