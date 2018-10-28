package com.spring.cloud.secure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

@Configuration
public class ServiceConfig {
	
	@Bean
	public OAuth2RestTemplate auth2RestTemplate (OAuth2ProtectedResourceDetails auth2ProtectedResourceDetails, OAuth2ClientContext auth2ClientContext) {
		return new OAuth2RestTemplate(auth2ProtectedResourceDetails, auth2ClientContext);
	}
}
