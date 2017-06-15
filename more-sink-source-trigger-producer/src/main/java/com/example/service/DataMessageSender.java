package com.example.service;


import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import com.example.data.Data;
import com.example.data.ASource;

@Service
@EnableBinding(ASource.class)
public class DataMessageSender{

    private ASource source;
    
    @Autowired
    private ThreadPoolTaskExecutor executor;
    
    @Autowired
    public DataMessageSender(ASource source) {
        this.source = source;
    }

    public boolean sendMessage(Data data) {
        return this.source.output().send(MessageBuilder.withPayload(data).build());
    }
    
    public void sendMessageAsync(Data data) throws Exception{
    	
    	DataMessageSenderThread thread = new DataMessageSenderThread(source, data);
    	ListenableFuture<Boolean> future = executor.submitListenable(thread);
    	// register a callback with the listener to receive the result of the send asynchronously
    	future.addCallback(thread.successCallback(data), thread.failureCallback(data));
    }
    
    
}