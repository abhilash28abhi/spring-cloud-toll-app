#Spring cloud task are short lived,asynchronous micro services which are a standalone executable applications that can be used for on-demand use-cases such as database migration, performing some cron jobs ,scheduled operations. These applications can run independently on variety of runtime platforms. These tasks are would be completed once you start it, it will not behave as a server or web app. We can store the history of these tasks in different ways, here we are using MySQL to do that. This helps in understanding what this task has done and what is the result or status.

#Spring cloud sleuth allows automatic instrumentation of communication channels. It gives near real time data collection of timing information and you can trace information to flow through the call tree path. Integrates well with Zipkin which helps in visualizing the latency.
	1. Span: It is an individual operation that happened having time stamped events attached to it.
	2. Trace: It is an end to end latency graph made up of spans.
	3. Annotation: 
		a. Client-sent: Means client made a request
		b. Server received: Means the server got that request
		c. Server sent: Means the server has finished processing and returns a response
		d. Client received: Means end of the span, the client got the response
	4. Tracer: It runs inside the production apps and creates these spans.
		