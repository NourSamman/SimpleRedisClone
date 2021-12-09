package com.redis.clone.client.backend;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.redis.clone.client.backend.model.Client;

@Service
public class ClientService {

	Logger logger = LoggerFactory.getLogger(ClientService.class);

	private static final String GET_SET_PATH = "variable/{varName}";
	private static final String INCR_PATH = "incr/{varName}";
	private static final String DECR_PATH = "decr/{varName}";
	private static final String RPUSH_PATH = "rPush/{varName}";
	private static final String LPUSH_PATH = "lPush/{varName}";
	private static final String RPOP_PATH = "rPop/{varName}";
	private static final String LPOP_PATH = "lPop/{varName}";
	private static final String LINDEX_PATH = "lIndex/{varName}";
	private static final String EXPIRE_PATH = "e/{varName}";

	@Autowired
	private RestTemplate restTemplate;

	public ClientService() {
		logger.info(">>> Init Client Controller");
	}

	public Client setVariable(String key, String variable) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", key);
		ResponseEntity<Client> responseEntity = restTemplate.postForEntity(GET_SET_PATH, variable, Client.class,
				params);
		return responseEntity.getBody();
	}

	public Client getVariable(String key) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", key);
		ResponseEntity<Client> responseEntity = restTemplate.getForEntity(GET_SET_PATH, Client.class, params);

		return responseEntity.getBody();
	}
}
