package com.redis.clone.store.model.exceptions;

public class IndexOutOfBoundsException extends RedisStoreException {

	private static final long serialVersionUID = 1L;

	private static final String IOOB_MSG = "Index Out of Bounds - Index: %d";

	public IndexOutOfBoundsException(int index) {
		super(String.format(IOOB_MSG, index));
	}

}
