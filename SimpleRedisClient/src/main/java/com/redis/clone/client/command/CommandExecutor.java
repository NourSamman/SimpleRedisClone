package com.redis.clone.client.command;

import org.apache.http.conn.HttpHostConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;

import com.redis.clone.client.backend.ClientService;
import com.redis.clone.client.backend.model.Client;
import com.redis.clone.client.command.model.CommandModel;
import com.redis.clone.client.exception.ConnectionException;
import com.redis.clone.client.exception.InternalErrorException;

@Controller
public class CommandExecutor {

	Logger logger = LoggerFactory.getLogger(CommandExecutor.class);

	private ClientService clientService;

	@Autowired
	public CommandExecutor(ClientService clientService) {
		this.clientService = clientService;
		logger.info(">>> Init Command Executor");
	}

	public Client execute(CommandModel commandModel) throws ConnectionException, InternalErrorException {

		logger.info(">>> Executing " + commandModel);

		Client response = new Client();
		try {
			switch (commandModel.getOperation()) {
			case SET:
				response = clientService.setVariable(commandModel.getVarName(), commandModel.getToBeStoredValue());
				break;
			case GET:
				response = clientService.getVariable(commandModel.getVarName());
				break;
			case INCR:
				response = clientService.incr(commandModel.getVarName(), commandModel.getToBeStoredValue());
				break;
			case DECR:
				response = clientService.decr(commandModel.getVarName(), commandModel.getToBeStoredValue());
				break;
			case RPUSH:
				response = clientService.rPush(commandModel.getVarName(), commandModel.getToBeStoredValue());
				break;

			case LPUSH:
				response = clientService.lPush(commandModel.getVarName(), commandModel.getToBeStoredValue());
				break;
			case RPOP:
				response = clientService.rPop(commandModel.getVarName());
				break;
			case LPOP:
				response = clientService.lPop(commandModel.getVarName());
				break;
			case LINDEX:
				response = clientService.lIndex(commandModel.getVarName(), commandModel.getToBeStoredValue());
				break;
			case EXPIRE:
				response = clientService.expire(commandModel.getVarName(), commandModel.getToBeStoredValue());
				break;
			}

		} catch (Exception restClientException) {
			logger.error(restClientException.getMessage());
			if (restClientException.getCause() != null
					&& restClientException.getCause() instanceof HttpHostConnectException)
				throw new ConnectionException();
			else
				throw new InternalErrorException();
		}
		
		logger.info(">>> Response - " + response);

		return response;
	}
}
