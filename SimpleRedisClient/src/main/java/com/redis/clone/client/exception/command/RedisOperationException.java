package com.redis.clone.client.exception.command;

public class RedisOperationException extends CommandException {

	private static final long serialVersionUID = 1L;

	private static final String INVALID_OPERATION = "%s is not recognized as a Redis Command.";

	public RedisOperationException(String operation) {
		super(String.format(INVALID_OPERATION, operation));
	}
}
