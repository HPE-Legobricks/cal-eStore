package com.hpe.calEStore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * This class holds & represent the configurations used by the CalEStore
 * application.
 * 
 * @author Suresh Babu Bikkina
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.hpe.calEStore" })
@PropertySource("classpath:calEStore.properties")
public class AppConfig {

	@Value("${calestore.restapi.url}")
	private String restApiUrl;

	public String getRestApiUrl() {
		return restApiUrl;
	}

}