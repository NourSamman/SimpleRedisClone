package com.redis.clone.client.exception.command;

public class IntegerVariableTypeException extends CommandException {

	private static final long serialVersionUID = 1L;
	
	private static final String INVALID_OPERATION = "%s is not an Integer value.";

	public IntegerVariableTypeException(String input) {
		super(String.format(INVALID_OPERATION, input));
	}
	
}
