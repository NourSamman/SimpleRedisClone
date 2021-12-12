package com.redis.clone.client.backend;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
	private static final String EXPIRE_PATH = "expire/{varName}";

	private HttpHeaders headers;

	@Autowired
	private RestTemplate restTemplate;

	public ClientService() {
		logger.info(">>> Init Client Service");

		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	public Client setVariable(String varName, Object variable) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", varName);
		HttpEntity<Object> request = new HttpEntity<Object>(variable, headers);

		ResponseEntity<Client> responseEntity = restTemplate.postForEntity(GET_SET_PATH, request, Client.class, params);
		return responseEntity.getBody();
	}

	public Client getVariable(String key) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", key);
		ResponseEntity<Client> responseEntity = restTemplate.getForEntity(GET_SET_PATH, Client.class, params);

		return responseEntity.getBody();
	}

	public Client incr(String varName, Object count) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", varName);

		HttpEntity<Object> body = new HttpEntity<Object>(count, headers);

		ResponseEntity<Client> responseEntity = restTemplate.exchange(INCR_PATH, HttpMethod.PUT, body, Client.class,
				params);
		return responseEntity.getBody();

	}

	public Client decr(String varName, Object count) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", varName);

		HttpEntity<Object> body = new HttpEntity<Object>(count, headers);

		ResponseEntity<Client> responseEntity = restTemplate.exchange(DECR_PATH, HttpMethod.PUT, body, Client.class,
				params);
		return responseEntity.getBody();

	}

	public Client lPush(String varName, Object storedVariable) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", varName);

		HttpEntity<Object> body = new HttpEntity<Object>(storedVariable, headers);

		ResponseEntity<Client> responseEntity = restTemplate.exchange(LPUSH_PATH, HttpMethod.PUT, body, Client.class,
				params);
		return responseEntity.getBody();

	}

	public Client lPop(String varName) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", varName);

		HttpEntity<Object> body = new HttpEntity<Object>(headers);

		ResponseEntity<Client> responseEntity = restTemplate.exchange(LPOP_PATH, HttpMethod.PUT, body, Client.class,
				params);
		return responseEntity.getBody();

	}

	public Client rPush(String varName, Object storedVariable) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", varName);

		HttpEntity<Object> body = new HttpEntity<Object>(storedVariable, headers);

		ResponseEntity<Client> responseEntity = restTemplate.exchange(RPUSH_PATH, HttpMethod.PUT, body, Client.class,
				params);
		return responseEntity.getBody();

	}

	public Client rPop(String varName) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", varName);

		HttpEntity<Object> body = new HttpEntity<Object>(headers);

		ResponseEntity<Client> responseEntity = restTemplate.exchange(RPOP_PATH, HttpMethod.PUT, body, Client.class,
				params);
		return responseEntity.getBody();

	}

	public Client lIndex(String varName, Object index) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", varName);

		HttpEntity<Object> body = new HttpEntity<Object>(index, headers);

		ResponseEntity<Client> responseEntity = restTemplate.exchange(LINDEX_PATH, HttpMethod.PUT, body, Client.class,
				params);
		return responseEntity.getBody();

	}

	public Client expire(String varName, Object seconds) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("varName", varName);

		HttpEntity<Object> body = new HttpEntity<Object>(seconds, headers);

		ResponseEntity<Client> responseEntity = restTemplate.exchange(EXPIRE_PATH, HttpMethod.PUT, body, Client.class,
				params);
		return responseEntity.getBody();

	}
}
