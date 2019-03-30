package com.learnreactivespring.fluxandmonoplaygound;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoErrorTest {

	@Test
	public void fluxErrorHandling() {
		Flux<String> stringFlux = Flux.just("A", "B", "C")
				.concatWith(Flux.error(new RuntimeException("Exception Occured")))
				.concatWith(Flux.just("D"))
				.onErrorResume(e -> {
					System.err.print(e);
					return Flux.just("default");
				});

		StepVerifier.create(stringFlux)
				.expectSubscription()
				.expectNext("A", "B", "C")
				.expectNext("default")
				.verifyComplete();
	}
	
	@Test
	public void fluxErrorHandling_OnErrorReturn() {
		Flux<String> stringFlux = Flux.just("A", "B", "C")
				.concatWith(Flux.error(new RuntimeException("Exception Occured")))
				.concatWith(Flux.just("D"))
				.onErrorReturn("default");
		
		StepVerifier.create(stringFlux)
		.expectSubscription()
		.expectNext("A", "B", "C")
		.expectNext("default")
		.verifyComplete();
	}
	@Test
	public void fluxErrorHandling_OnErrorMap() {
		Flux<String> stringFlux = Flux.just("A", "B", "C")
				.concatWith(Flux.error(new RuntimeException("Exception Occured")))
				.concatWith(Flux.just("D"))
				.onErrorMap(CustomException::new);
		
		StepVerifier.create(stringFlux)
		.expectSubscription()
		.expectNext("A", "B", "C")
		.expectError(CustomException.class)
		.verify();
	}
	
	@Test
	public void fluxErrorHandling_OnErrorMap_withRetry() {
		Flux<String> stringFlux = Flux.just("A", "B", "C")
				.concatWith(Flux.error(new RuntimeException("Exception Occured")))
				.concatWith(Flux.just("D"))
				.onErrorMap(CustomException::new)
				.retry(2);
		
		StepVerifier.create(stringFlux)
		.expectSubscription()
		.expectNext("A", "B", "C")
		.expectNext("A", "B", "C")
		.expectNext("A", "B", "C")
		.expectError(CustomException.class)
		.verify();
	}
	
	@Test
	public void fluxErrorHandling_OnErrorMap_withRetryBackoff() {
		Flux<String> stringFlux = Flux.just("A", "B", "C")
				.concatWith(Flux.error(new RuntimeException("Exception Occured")))
				.concatWith(Flux.just("D"))
				.onErrorMap(CustomException::new)
				.retryBackoff(2, Duration.ofSeconds(1));
		
		StepVerifier.create(stringFlux)
		.expectSubscription()
		.expectNext("A", "B", "C")
		.expectNext("A", "B", "C")
		.expectNext("A", "B", "C")
		.expectError(IllegalStateException.class)
		.verify();
	}

}
