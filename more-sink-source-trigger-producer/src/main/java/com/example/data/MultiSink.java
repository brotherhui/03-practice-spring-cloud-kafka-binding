package com.example.data;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MultiSink {
	String SINK1 = "input1";

	@Input(SINK1)
	//here is very imoprtant too
	//name the method to the sink
	SubscribableChannel input1();
}
