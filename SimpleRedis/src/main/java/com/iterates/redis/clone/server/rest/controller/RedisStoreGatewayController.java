package com.iterates.redis.clone.server.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("variable/{varName}")
	public Object get(@PathVariable String varName) {
		try {
			return redisStoreGateway.get(varName);
		} catch (RedisStoreException e) {
			return e.getMessage();

		}
	}

	@PostMapping("variable/{varName}")
	public String set(@PathVariable String varName, @RequestBody Object value) {
		try {
			redisStoreGateway.set(varName, value);
			return "Success";
		} catch (RedisStoreException e) {
			return e.getMessage();
		}
	}

	@PutMapping("incr/{varName}")
	public String incr(@PathVariable String varName, @RequestBody int count) {
		try {
			redisStoreGateway.incr(varName, count);
			return "Success";
		} catch (RedisStoreException e) {
			return e.getMessage();
		}
	}

	@PutMapping("decr/{varName}")
	public String decr(@PathVariable String varName, @RequestBody int count) {
		try {
			redisStoreGateway.decr(varName, count);
			return "Success";
		} catch (RedisStoreException e) {
			return e.getMessage();
		}
	}

	@PutMapping("rPush/{varName}")
	public String rPush(@PathVariable String varName, @RequestBody Object count) {
		try {
			redisStoreGateway.rPush(varName, count);
			return "Success";
		} catch (RedisStoreException e) {
			return e.getMessage();
		}
	}

	@PutMapping("rPop/{varName}")
	public Object rPop(@PathVariable String varName) {
		try {
			return redisStoreGateway.rPop(varName);

		} catch (RedisStoreException e) {
			return e.getMessage();
		}
	}

	@PutMapping("lPush/{varName}")
	public String lPush(@PathVariable String varName, @RequestBody Object count) {
		try {
			redisStoreGateway.lPush(varName, count);
			return "Success";
		} catch (RedisStoreException e) {
			return e.getMessage();
		}
	}

	@PutMapping("lPop/{varName}")
	public Object lPop(@PathVariable String varName) {
		try {
			return redisStoreGateway.lPop(varName);

		} catch (RedisStoreException e) {
			return e.getMessage();
		}
	}

	@PutMapping("lIndex/{varName}")
	public Object lIndex(@PathVariable String varName, @RequestBody int index) {
		try {
			return redisStoreGateway.lIndex(varName, index);
		} catch (RedisStoreException e) {
			return e.getMessage();
		}
	}
}
