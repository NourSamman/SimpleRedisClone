package com.redis.clone.client.views.command;

import com.redis.clone.client.views.command.exception.InvalidCommandException;

public class CommandParser {

	private String command;

	public void parseCommand(String command) throws Exception {

		String[] commandPillars = command.split("\\s+");

		if (commandPillars.length == 2) {
			
		} else if (commandPillars.length == 3) {

		} else
			throw new InvalidCommandException();

	}

	public void execute() throws Exception {

	}

}
