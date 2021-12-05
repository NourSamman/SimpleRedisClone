package com.iterates.redis.clone.store.model.exceptions;

public class NotAllowedVariableError extends RedisStoreException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7631933507192249460L;

	private static final String MESSAGE = "Not Allowed: %s";

	public NotAllowedVariableError(String message) {
		super(String.format(MESSAGE, message));
	}

}
