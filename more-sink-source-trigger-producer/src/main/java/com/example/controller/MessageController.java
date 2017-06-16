package com.example.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.KafkaMessageSender;
import com.example.common.KafkaSourceThread;
import com.example.data.ASource;
import com.example.data.Data;
import com.example.service.DataMessageSenderThread;

@RestController
public class MessageController {

	@Autowired
    private KafkaMessageSender messageSender;
	
	@Autowired
	private ASource source;

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String id) {
    	this.messageSender.sendMessage(source, new Data(new Date(),id));       
        return ResponseEntity.ok("Send message to Kafka");
    }
    
    @GetMapping("/sendasync")
    public ResponseEntity<String> sendMessageAsync() {
    	for(int i=0; i<100; i++){
    		try{
    			KafkaSourceThread thread = new DataMessageSenderThread();
    			this.messageSender.sendMessageAsync(thread, source, new Data(new Date(),""+i));
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    	    
        return ResponseEntity.ok("Send message to Kafka");
    }
}
