package com.example.data;

import java.util.Date;

public class Data {
	
	private Date processed_at; 
	private String processor_version;
	
	public Data(){
		
	}
	
	public Data(Date processed_at, String processor_version) {
		super();
		this.processed_at = processed_at;
		this.processor_version = processor_version;
	}

	public Date getProcessed_at() {
		return processed_at;
	}

	public void setProcessed_at(Date processed_at) {
		this.processed_at = processed_at;
	}

	public String getProcessor_version() {
		return processor_version;
	}

	public void setProcessor_version(String processor_version) {
		this.processor_version = processor_version;
	}
	
	@Override
	public String toString() {
		return "Data [time=" + this.processed_at + ", label=" + this.processor_version + "]";
	}

}