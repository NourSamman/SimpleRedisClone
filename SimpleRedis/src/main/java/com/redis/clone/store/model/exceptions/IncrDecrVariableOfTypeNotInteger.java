package com.redis.clone.store.model.exceptions;

public class IncrDecrVariableOfTypeNotInteger extends RedisStoreException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5987791223093057242L;

	private static final String MESSAGE = "Variable is not an Integer to be Incr or Decr";

	public IncrDecrVariableOfTypeNotInteger() {
		super(MESSAGE);
	}

}
