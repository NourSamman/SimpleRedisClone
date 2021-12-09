package com.redis.clone.client.backend.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class ClientAPIConfiguration {

	Logger logger = LoggerFactory.getLogger(ClientAPIConfiguration.class);

	@Value("${api.host.url}")
	private String apiHost;

	@Bean
	public RestTemplate restTemplate() {

		logger.info("Init Rest Template...");

		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setHttpClient(HttpClientBuilder.create().build());
		clientHttpRequestFactory.setReadTimeout(3000);
		clientHttpRequestFactory.setConnectTimeout(3000);

		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(apiHost));

		return restTemplate;
	}
}
