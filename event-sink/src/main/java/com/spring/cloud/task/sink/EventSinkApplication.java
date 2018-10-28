package com.spring.cloud.task.sink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.launcher.annotation.EnableTaskLauncher;

@SpringBootApplication
@EnableTaskLauncher
public class EventSinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventSinkApplication.class, args);
	}
}
