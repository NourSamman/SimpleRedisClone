package com.redis.clone.client.exception.command;

public class InvalidNumberArgumentException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String OPERATION_DEFINITION_MSG = "Invalid Arguments - %s";

	public InvalidNumberArgumentException(String operationDefinition) {
		super(String.format(OPERATION_DEFINITION_MSG, operationDefinition));
	}
}
