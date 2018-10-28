package com.spring.cloud.auth.server.secureauthserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class ServiceConfig extends GlobalAuthenticationConfigurerAdapter {
	
	@Override
	public void init (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.inMemoryAuthentication()
		.withUser("abhilash").password("{noop}test").roles("USER").and()
		.withUser("admin").password("{noop}admin").roles("USER", "OPERATOR");
	}
}
