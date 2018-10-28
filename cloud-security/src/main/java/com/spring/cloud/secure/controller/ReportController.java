package com.spring.cloud.secure.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableOAuth2Sso
public class ReportController extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private OAuth2ClientContext clientContext;
	
	@Autowired
	private OAuth2RestTemplate auth2RestTemplate;
	
	@RequestMapping("/")
	public String loadHome () {
		return "home";
	}
	
	@RequestMapping("/reports")
	public String loadReport (Model model) {
		OAuth2AccessToken token = clientContext.getAccessToken();
		System.out.println("Token" + token);
		
		//making a call to reports endpoint
		ResponseEntity<ArrayList<TollUsage>> tolls = auth2RestTemplate.exchange("http://localhost:9001/services/tolldata", HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<TollUsage>>(){});
		model.addAttribute("tolls", tolls.getBody());
		return "reports";
	}
	
	@Override
	public void configure (HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "/login")
		.permitAll()
		.anyRequest()
		.authenticated();
	}
	
	public static class TollUsage {
		public String Id;
		public String stationId;
		public String licensePlate;
		public String timeStamp;
		
		public TollUsage () {}

		public TollUsage(String id, String stationId, String licensePlate, String timeStamp) {
			this.Id = id;
			this.stationId = stationId;
			this.licensePlate = licensePlate;
			this.timeStamp = timeStamp;
		}
	}
}
