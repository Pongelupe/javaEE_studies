package com.learnreactivespring.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import com.learnreactivespring.handler.SampleHandlerFunction;

@Configuration
public class RouterFunctionConfig {

	@Bean
	public RouterFunction<ServerResponse> route(SampleHandlerFunction handler) {
		return RouterFunctions.route(GET("/functional/flux").and(accept(MediaType.APPLICATION_JSON_UTF8)), handler::flux)
				.andRoute(GET("/functional/mono").and(accept(MediaType.APPLICATION_JSON_UTF8)), handler::mono);
	}

}
