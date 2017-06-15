package com.example.service;

import org.springframework.cloud.stream.messaging.Source;

import com.example.common.MessageCommonThread;

public class DataMessageSenderThread extends MessageCommonThread{

	public DataMessageSenderThread(Source source, Object payload) {
		super(source, payload);
		// TODO Auto-generated constructor stub
	}

}
