package com.redis.clone.client.command.model;

public class CommandModel {

	private CommandOperation operation;
	private String varName;
	private Object storedValue;

	public CommandModel(CommandOperation operation, String varName, Object storedValue) {
		super();
		this.operation = operation;
		this.varName = varName;
		this.storedValue = storedValue;
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

	public Object getStoredValue() {
		return storedValue;
	}

	public void setStoredValue(Object storedValue) {
		this.storedValue = storedValue;
	}

	@Override
	public String toString() {
		return "CommandModel [operation=" + operation + ", varName=" + varName + ", storedValue=" + storedValue + "]";
	}

}
