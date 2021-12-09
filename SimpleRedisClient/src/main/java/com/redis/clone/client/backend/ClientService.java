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

	@Autowired
	private RestTemplate restTemplate;

	public ClientService() {
		logger.info("Init Client Controller...");
	}

	public Client setVariable(String key, String variable) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", key);
		ResponseEntity<Client> responseEntity = restTemplate.postForEntity("variable/{varName}", variable, Client.class,
				params);
		return responseEntity.getBody();
	}

	public Client getVariable(String key) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", key);
		ResponseEntity<Client> responseEntity = restTemplate.getForEntity("variable/{varName}", Client.class, params);

		return responseEntity.getBody();
	}
}
