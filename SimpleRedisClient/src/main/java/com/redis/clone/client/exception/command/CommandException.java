package com.redis.clone.client.exception.command;

public class CommandException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Invalid Command: %s";

	public CommandException(String message) {
		super(String.format(MESSAGE, message));
	}

}
