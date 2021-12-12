package com.redis.clone.client.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redis.clone.client.command.model.CommandModel;
import com.redis.clone.client.command.model.CommandOperation;
import com.redis.clone.client.exception.command.IntegerVariableTypeException;
import com.redis.clone.client.exception.command.InvalidNumberArgumentException;

public class CommandParser {

	Logger logger = LoggerFactory.getLogger(CommandParser.class);

	public CommandModel parseCommand(String command) throws Exception {

		String[] commandPillars = command.split("\\s+");

		CommandOperation operation = CommandOperation.isAcceptableOperation(commandPillars[0]);

		if (commandPillars.length < 2 || commandPillars.length > 3
				|| (operation.isA(CommandOperation.GET, CommandOperation.LPOP, CommandOperation.RPOP)
						&& commandPillars.length > 2)
				|| (!operation.isA(CommandOperation.GET, CommandOperation.LPOP, CommandOperation.RPOP)
						&& commandPillars.length != 3))
			throw new InvalidNumberArgumentException(operation.getOperationDefinition());

		String varName = commandPillars[1];

		Object storedValue = null;

		if (commandPillars.length != 2)
			if (operation.isIntegerValueNeeded())
				storedValue = parseToInt(commandPillars[2]);
			else
				storedValue = commandPillars[2];

		return new CommandModel(operation, varName, storedValue);

	}

	private Integer parseToInt(String input) throws IntegerVariableTypeException {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			logger.info(e.getMessage());
			throw new IntegerVariableTypeException(input);
		}
	}
}
