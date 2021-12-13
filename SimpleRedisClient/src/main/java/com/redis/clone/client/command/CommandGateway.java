package com.redis.clone.client.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.redis.clone.client.backend.model.Client;
import com.redis.clone.client.command.model.CommandModel;
import com.redis.clone.client.command.model.CommandOperation;

@Component
public class CommandGateway {


	private CommandExecutor commandExecutor;

	@Autowired
	public CommandGateway(CommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
	}

	public String run(String command) throws Exception {

		CommandModel commandModel = CommandParser.parseCommand(command);
		Client response = commandExecutor.execute(commandModel);
		String message = resolveClient(response, commandModel);

		return message;
	}

	public String resolveClient(Client c, CommandModel command) {

		if (c.getHttpStatus() == HttpStatus.INTERNAL_SERVER_ERROR)
			return "Server Error: " + c.getMessage();
		else if (c.getHttpStatus() == HttpStatus.NOT_FOUND)
			return c.getHttpStatus().getReasonPhrase() + ": " + c.getMessage();
		if (command.getOperation().isA(CommandOperation.GET, CommandOperation.RPOP, CommandOperation.LPOP,
				CommandOperation.LINDEX))
			return "Success: value => " + c.getReturnedObject();
		else
			return c.getHttpStatus().getReasonPhrase();
	}
}
