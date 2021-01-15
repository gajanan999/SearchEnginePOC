package com.searchengine.model;

import org.springframework.http.HttpStatus;

public class ResponseModel {
	
	HttpStatus message;
	
	
	Data data;

	public HttpStatus getMessage() {
		return message;
	}

	public void setMessage(HttpStatus message) {
		this.message = message;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	
}
