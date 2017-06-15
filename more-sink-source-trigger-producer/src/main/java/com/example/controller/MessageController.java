package com.example.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.data.Data;
import com.example.service.MessageSender;

@RestController
public class MessageController {

	@Autowired
    private MessageSender messageSender;

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String id) {
    	//use for to check if it is sync or async
    	//actually this is sync
    	for(int i=0;i<1000;i++){
    		 this.messageSender.sendMessage(new Data(new Date(),""+i));
    	}
       
        return ResponseEntity.ok("Send message to Kafka");
    }
}
