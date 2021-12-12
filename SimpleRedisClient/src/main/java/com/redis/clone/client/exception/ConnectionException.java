package com.redis.clone.client.exception;

public class ConnectionException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String CON_MSG = "Connection Problem: Couldn't connect to Redis Server.";

	public ConnectionException() {
		super(CON_MSG);
	}

}
