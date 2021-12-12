package com.redis.clone.client.exception;

public class InternalErrorException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String INTERNAL_MSG = "Internal Error occured";

	public InternalErrorException() {
		super(INTERNAL_MSG);
	}

}
