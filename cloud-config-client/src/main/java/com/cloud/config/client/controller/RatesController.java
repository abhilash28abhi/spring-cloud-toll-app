package com.cloud.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/*Thymeleaf templates doesnt work with RestController. RestController 
annotation returns the json from the method not HTML or JSP. 
It is the combination of @Controller and @ResponseBody in one. 
The main purpose of @RestController is to create RESTful web services. 
For returning html or jsp, simply annotated the controller class with @Controller*/
@RefreshScope
//Spring will refresh all the beans marked with RefreshScope when the /actuator/refresh endpoint is called
public class RatesController {
	@Value("${rate}")
	String rate;
	
	@Value("${lanecount}")
	String laneCount;
	
	@Value("${tollstart}")
	String tollStart;
	
	@Value("${connstring}")
	String connString;
	
	@RequestMapping("/rate")
	public String getModel (Model model) {
		model.addAttribute("rateamount", rate);
		model.addAttribute("lanes", laneCount);
		model.addAttribute("tollstart", tollStart);
		model.addAttribute("connstring", connString);
		//name of the view
		return "rateview";
	}
}
