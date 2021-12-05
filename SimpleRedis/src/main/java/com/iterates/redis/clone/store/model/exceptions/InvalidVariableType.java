package com.iterates.redis.clone.store.model.exceptions;

public class InvalidVariableType extends RedisStoreException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6918307787043096007L;

	private static final String MESSAGE = "Invalid variable Type";

	public InvalidVariableType() {
		super(MESSAGE);
	}

}
