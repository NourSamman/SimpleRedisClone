package com.redis.clone.client.views.command.exception;

public class InvalidCommandException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Invalid Command: %s";
	private static final String DEFAULT_MESSAGE = "Invalid Command";

	public InvalidCommandException() {
		super(DEFAULT_MESSAGE);
	}

	public InvalidCommandException(String message) {
		super(String.format(MESSAGE, message));
	}

}
