package com.redis.clone.api.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	private LocalDateTime timeStamp;
	private int statusCode;
	private HttpStatus httpStatus;
	private String message;

	private Object returnedObject;

	public Response(LocalDateTime timeStamp, int statusCode, HttpStatus httpStatus, String message,
			Object returnedObject) {
		super();
		this.timeStamp = timeStamp;
		this.statusCode = statusCode;
		this.httpStatus = httpStatus;
		this.message = message;
		this.returnedObject = returnedObject;
	}

	public Response(LocalDateTime timeStamp, int statusCode, HttpStatus httpStatus, String message) {
		super();
		this.timeStamp = timeStamp;
		this.statusCode = statusCode;
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getReturnedObject() {
		return returnedObject;
	}

	public void setReturnedObject(Object returnedObject) {
		this.returnedObject = returnedObject;
	}

}
