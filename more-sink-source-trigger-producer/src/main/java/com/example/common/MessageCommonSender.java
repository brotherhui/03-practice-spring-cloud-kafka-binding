package com.example.common;


import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.service.DataMessageSenderThread;

@Service
public class MessageCommonSender{
    
    @Autowired
    private ThreadPoolTaskExecutor executor;

    public <T> boolean sendMessage(Source source, T data) {
        return source.output().send(MessageBuilder.withPayload(data).build());
    }
    
    public <T> void sendMessageAsync(Source source, T data) throws Exception{
    	DataMessageSenderThread thread = new DataMessageSenderThread(source, data);
    	ListenableFuture<Boolean> future = executor.submitListenable(thread);
    	// register a callback with the listener to receive the result of the send asynchronously
    	future.addCallback(thread.successCallback(data), thread.failureCallback(data));
    }
    
    
}