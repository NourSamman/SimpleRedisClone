package com.iterates.redis.clone.server.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.iterates.redis.clone.model.exceptions.RedisStoreException;
import com.iterates.redis.clone.server.gateway.RedisStoreGateway;

@RestController
public class RedisStoreGatewayController {
	
	private RedisStoreGateway redisStoreGateway;
	
    Logger logger = LoggerFactory.getLogger(RedisStoreGateway.class);

	@Autowired
	public RedisStoreGatewayController(RedisStoreGateway redisStoreGateway) {
		this.redisStoreGateway = redisStoreGateway;
		logger.info("REST API IS STARTED");
		
	}
	
	@GetMapping("/variable/{varName}")
	public Object get(@PathVariable String varName) {
		try {
			return redisStoreGateway.get(varName);
		} catch (RedisStoreException e) {
			return e.getMessage();
		}
	}
	
	@PutMapping("/variable/{varName}")
	public String  set(@PathVariable String varName, @RequestBody Object value) {
		try {
			redisStoreGateway.set(varName, value);
			return "Success";
		} catch (RedisStoreException e) {
			return e.getMessage();
		}
	}

}
