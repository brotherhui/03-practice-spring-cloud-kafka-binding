package com.example.common;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;


public abstract class MessageCommonThread<T> implements Callable<Object>{
	
	private static Logger logger = LoggerFactory.getLogger(MessageCommonThread.class);
	
	private Source source;
	private T payload;
	
	public MessageCommonThread(Source source, T payload){
		this.source = source;
		this.payload = payload;
	}
	
    @Override
    public Boolean call() {
    	return source.output().send(MessageBuilder.withPayload(payload).build());
    }
    
    
	public SuccessCallback<Boolean> successCallback(T o) {
		return new SuccessCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				logger.debug("Success to produce message. Message is {}.", result);
			}
		};
	}
	
	public FailureCallback failureCallback(T o) {
		return new FailureCallback() {
			@Override
			public void onFailure(Throwable ex) {
				logger.error("Error in producing Mseeage to Kafka!", ex);
			}

		};
	}

}
