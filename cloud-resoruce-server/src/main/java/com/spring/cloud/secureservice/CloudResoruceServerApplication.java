package com.spring.cloud.secureservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
public class CloudResoruceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudResoruceServerApplication.class, args);
	}
	
	@Autowired
	private ResourceServerProperties sso;
	
	@Bean
	public ResourceServerTokenServices myUserInfoTokenServices() {
		return new CustomUserInfoTokenService(sso.getUserInfoUri(), sso.getClientId());
	}
	
	@RequestMapping("/tolldata")
	//request endpoint would be /services/tolldata (services from the context path)
	@PreAuthorize("#oauth2.hasScope('toll_read') and hasAuthority('ROLE_OPERATOR')")
	public ArrayList<TollUsage> getTollData() {
		
		TollUsage instance1 = new TollUsage("100", "station150", "B65GT1W", "2018-09-30T06:31:22");
		TollUsage instance2 = new TollUsage("101", "station119", "AHY673B", "2018-09-30T06:32:50");
		TollUsage instance3 = new TollUsage("102", "station150", "ZN2GP0", "2018-09-30T06:37:01");
		
		ArrayList<TollUsage> tolls = new ArrayList<TollUsage>();
		tolls.add(instance1);
		tolls.add(instance2);
		tolls.add(instance3);
		return tolls;
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
