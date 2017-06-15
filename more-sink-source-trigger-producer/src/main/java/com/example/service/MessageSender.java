package com.example.service;


import com.example.data.Data;
import com.example.data.MultiSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by pkpk1234 on 2017/5/25.
 */

@Service
@EnableBinding(MultiSource.class)
public class MessageSender {

    private MultiSource source;

    @Autowired
    public MessageSender(MultiSource source) {
        this.source = source;
    }

    @Async
    public void sendMessage(Data data) {
        this.source.output1().send(MessageBuilder.withPayload(data).build());
    }
    
}