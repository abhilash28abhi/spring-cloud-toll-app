package com.spring.cloud.task.intake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
	
	@Autowired
	private TaskProcessor task;
	
	@PostMapping(path="/tasks")
	public String launchTask(@RequestBody String body) {
		task.publishRequest(body);
		System.out.println("request made");
		return "success";
	}
}
