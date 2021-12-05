package com.iterates.redis.clone.api.controller;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iterates.redis.clone.api.model.Response;
import com.iterates.redis.clone.store.RedisStoreGateway;
import com.iterates.redis.clone.store.model.exceptions.RedisStoreException;

@RestController
public class RedisStoreGatewayController {

	private RedisStoreGateway redisStoreGateway;

	Logger logger = LoggerFactory.getLogger(RedisStoreGateway.class);
	private static String SUCCESS = "SUCCESS";

	@Autowired
	public RedisStoreGatewayController(RedisStoreGateway redisStoreGateway) {
		this.redisStoreGateway = redisStoreGateway;
		logger.info("REST API IS STARTED");

	}

	@GetMapping("variable/{varName}")
	public ResponseEntity<Response> get(@PathVariable String varName) {

		try {
			Object value = redisStoreGateway.get(varName);
			return ResponseEntity.ok(new Response(now(), OK.value(), OK, SUCCESS, value));

		} catch (RedisStoreException e) {
			logger.error(e.getMessage());
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("variable/{varName}")
	public ResponseEntity<Response> set(@PathVariable String varName, @RequestBody Object value) {

		try {
			redisStoreGateway.set(varName, value);
			return ResponseEntity.ok(new Response(now(), CREATED.value(), CREATED, SUCCESS));

		} catch (RedisStoreException e) {
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new Response(now(), INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

	@PutMapping("incr/{varName}")
	public ResponseEntity<Response> incr(@PathVariable String varName, @RequestBody int count) {

		try {
			redisStoreGateway.incr(varName, count);
			return ResponseEntity.ok(new Response(now(), OK.value(), OK, SUCCESS));

		} catch (RedisStoreException e) {
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new Response(now(), INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

	@PutMapping("decr/{varName}")
	public ResponseEntity<Response> decr(@PathVariable String varName, @RequestBody int count) {

		try {
			redisStoreGateway.decr(varName, count);
			return ResponseEntity.ok(new Response(now(), OK.value(), OK, SUCCESS));

		} catch (RedisStoreException e) {
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new Response(now(), INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

	@PutMapping("rPush/{varName}")
	public ResponseEntity<Response> rPush(@PathVariable String varName, @RequestBody Object count) {

		try {
			redisStoreGateway.rPush(varName, count);
			return ResponseEntity.ok(new Response(now(), OK.value(), OK, SUCCESS));

		} catch (RedisStoreException e) {
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new Response(now(), INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

	@PutMapping("rPop/{varName}")
	public ResponseEntity<Response> rPop(@PathVariable String varName) {

		try {
			Object returnedObject = redisStoreGateway.rPop(varName);
			return ResponseEntity.ok(new Response(now(), OK.value(), OK, "SUCCESS", returnedObject));

		} catch (RedisStoreException e) {
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new Response(now(), INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

	@PutMapping("lPush/{varName}")
	public ResponseEntity<Response> lPush(@PathVariable String varName, @RequestBody Object count) {

		try {
			redisStoreGateway.lPush(varName, count);
			return ResponseEntity.ok(new Response(now(), OK.value(), OK, SUCCESS));

		} catch (RedisStoreException e) {
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new Response(now(), INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

	@PutMapping("lPop/{varName}")
	public ResponseEntity<Response> lPop(@PathVariable String varName) {

		try {
			Object returnedObject = redisStoreGateway.lPop(varName);
			return ResponseEntity.ok(new Response(now(), OK.value(), OK, SUCCESS, returnedObject));

		} catch (RedisStoreException e) {
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new Response(now(), INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

	@PutMapping("lIndex/{varName}")
	public Object lIndex(@PathVariable String varName, @RequestBody int index) {

		try {
			Object returnedObject = redisStoreGateway.lIndex(varName, index);
			return ResponseEntity.ok(new Response(now(), OK.value(), OK, SUCCESS, returnedObject));

		} catch (RedisStoreException e) {
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new Response(now(), INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

	@PutMapping("expire/{varName}")
	public Object expire(@PathVariable String varName, @RequestBody long seconds) {

		try {
			redisStoreGateway.expires(varName, seconds);
			return ResponseEntity.ok(new Response(now(), OK.value(), OK, SUCCESS));

		} catch (RedisStoreException e) {
			logger.error(e.getMessage());
			return ResponseEntity.internalServerError()
					.body(new Response(now(), INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}
}
