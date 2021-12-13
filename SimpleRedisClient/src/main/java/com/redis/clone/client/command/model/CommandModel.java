package com.redis.clone.client.command.model;

public class CommandModel {

	private CommandOperation operation;
	private String varName;
	private Object toBeStoredValue;

	public CommandModel(CommandOperation operation, String varName, Object storedValue) {
		super();
		this.operation = operation;
		this.varName = varName;
		this.toBeStoredValue = storedValue;
	}

	public CommandOperation getOperation() {
		return operation;
	}

	public void setOperation(CommandOperation operation) {
		this.operation = operation;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public Object getToBeStoredValue() {
		return toBeStoredValue;
	}

	public void setToBeStoredValue(Object toBeStoredValue) {
		this.toBeStoredValue = toBeStoredValue;
	}

	@Override
	public String toString() {
		return "CommandModel [operation=" + operation + ", varName=" + varName + ", storedValue=" + toBeStoredValue + "]";
	}

}
