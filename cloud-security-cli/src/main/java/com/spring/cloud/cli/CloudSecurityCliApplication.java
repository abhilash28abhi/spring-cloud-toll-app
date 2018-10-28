package com.spring.cloud.cli;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@SpringBootApplication
public class CloudSecurityCliApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CloudSecurityCliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting cron job");
		ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
		details.setClientAuthenticationScheme(AuthenticationScheme.header);
		details.setAccessTokenUri("http://localhost:9000/services/oauth/token");
		details.setScope(Arrays.asList("toll_read"));
		details.setClientId("tollapp");
		details.setClientSecret("tollapp");
		details.setUsername("admin");
		details.setPassword("admin");
		
		OAuth2RestTemplate template = new OAuth2RestTemplate(details);
		System.out.println("Token : " + template.getAccessToken().toString());
		
		String str = template.getForObject("http://localhost:9001/services/tolldata", String.class);
		System.out.println("Result : " + str);
	}
}
