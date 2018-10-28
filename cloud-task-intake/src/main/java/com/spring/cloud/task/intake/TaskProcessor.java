package com.spring.cloud.task.intake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;


@Component
@EnableBinding(Processor.class)
public class TaskProcessor {

	
	//this will refer to the rabbitmq queue
	@Autowired
	private Processor channel;
	
	/**
	 *  Method to publish the request to the rabbit queue
	 * @param payload
	 */
	@Transformer(inputChannel=Processor.INPUT,outputChannel=Processor.OUTPUT)
	public void publishRequest(String payload) {
		//location of cloud task jar in local maven repo to be used by local cloud deployer
		String url = "maven://com.spring.cloud.task:cloud-task:jar:0.0.1-SNAPSHOT";
		
		List<String> input = new ArrayList<>(Arrays.asList(payload.split(",")));
		TaskLaunchRequest request = new TaskLaunchRequest(url, input, null, null);
		System.out.println("Created task request");
		
		GenericMessage<TaskLaunchRequest> message = new GenericMessage<>(request);
		this.channel.output().send(message);
	}
}
