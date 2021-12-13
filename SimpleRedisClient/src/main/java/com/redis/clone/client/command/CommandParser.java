package com.redis.clone.client.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redis.clone.client.command.model.CommandModel;
import com.redis.clone.client.command.model.CommandOperation;
import com.redis.clone.client.exception.command.IntegerVariableTypeException;
import com.redis.clone.client.exception.command.InvalidNumberArgumentException;

public final class CommandParser {

	static Logger logger = LoggerFactory.getLogger(CommandParser.class);

	public static CommandModel parseCommand(String command) throws Exception {

		String[] commandPillars = command.split("\\s+");

		CommandOperation operation = CommandOperation.isAcceptableOperation(commandPillars[0]);

		if (commandPillars.length < 2 || commandPillars.length > 3
				|| (operation.isA(CommandOperation.GET, CommandOperation.LPOP, CommandOperation.RPOP)
						&& commandPillars.length > 2)
				|| (!operation.isA(CommandOperation.GET, CommandOperation.LPOP, CommandOperation.RPOP,CommandOperation.INCR,CommandOperation.DECR)
						&& commandPillars.length != 3))
			throw new InvalidNumberArgumentException(operation.getOperationDefinition());

		String varName = commandPillars[1];

		Object storedValue = null;

		if (commandPillars.length != 2)
			if (operation.isIntegerValueNeeded())
				storedValue = parseToInt(commandPillars[2]);
			else
				storedValue = commandPillars[2];
		
		if(operation.isA(CommandOperation.INCR,CommandOperation.DECR) && storedValue == null)
			storedValue =1;
			
		return new CommandModel(operation, varName, storedValue);

	}

	private static Integer parseToInt(String input) throws IntegerVariableTypeException {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			logger.info(e.getMessage());
			throw new IntegerVariableTypeException(input);
		}
	}
}
