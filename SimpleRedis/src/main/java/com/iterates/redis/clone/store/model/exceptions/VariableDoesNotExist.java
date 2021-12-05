package com.iterates.redis.clone.store.model.exceptions;

public class VariableDoesNotExist extends RedisStoreException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3471582575871599619L;

	private static final String MESSAGE = "Variable doesn't exist";

	public VariableDoesNotExist() {
		super(MESSAGE);
	}

}
