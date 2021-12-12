package com.redis.clone.client.command.model;

import java.util.Optional;
import java.util.stream.Stream;

import com.redis.clone.client.exception.command.RedisOperationException;

public enum CommandOperation {

	SET, GET, INCR, DECR, RPUSH, LPUSH, RPOP, LPOP, LINDEX, EXPIRE;

	public String getOperationDefinition() {

		String operationDefinition = "Undefined Operation Definition!";

		if (this == SET || this == RPUSH || this == LPUSH)
			operationDefinition = this.name() + " takes two arguments: varName(str), variable(obj).";
		else if (this == INCR || this == DECR)
			operationDefinition = this.name() + " takes two arguments: varName(str), count(int).";
		else if (this == LINDEX)
			operationDefinition = this.name() + " takes two arguments: varName(str), index(int).";
		else if (this == EXPIRE)
			operationDefinition = this.name() + " takes two arguments: varName(str), timeout(int).";
		else if (this == GET || this == RPOP || this == LPOP)
			operationDefinition = this.name() + " takes one argument: varName(str).";

		return operationDefinition;
	}

	public Boolean isIntegerValueNeeded() {
		return isA(INCR, DECR, LINDEX, EXPIRE);
	}

	public Boolean isA(CommandOperation... commandOperations) {
		for (CommandOperation c : commandOperations)
			if (this == c)
				return true;

		return false;
	}

	public static CommandOperation isAcceptableOperation(String operation) throws RedisOperationException {

		Optional<CommandOperation> cmdOperation = Stream.of(CommandOperation.values())
				.filter(commandOperation -> operation.toUpperCase().equals(commandOperation.name())).findFirst();

		if (!cmdOperation.isPresent())
			throw new RedisOperationException(operation);

		return cmdOperation.get();
	}

}
