package com.learnreactivespring.fluxandmonoplaygound;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

	@Test
	public void fluxTest() {
		Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occurred"))).concatWith(Flux.just("After error"))
				.log();

		stringFlux.subscribe(System.out::println, System.err::println, () -> System.out.println("Completed"));
	}

	@Test
	public void fluxTestElements_withoutError() {
		Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring").log();

		StepVerifier.create(stringFlux).expectNext("Spring").expectNext("Spring Boot").expectNext("Reactive Spring")
				.verifyComplete();
	}

	@Test
	public void fluxTestElements_withError() {
		Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occurred"))).log();

		StepVerifier.create(stringFlux).expectNext("Spring").expectNext("Spring Boot").expectNext("Reactive Spring")
				.verifyErrorMessage("Exception Occurred");
	}

	@Test
	public void fluxTestElements_withError1() {
		Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occurred"))).log();

		StepVerifier.create(stringFlux).expectNext("Spring", "Spring Boot", "Reactive Spring")
				.verifyErrorMessage("Exception Occurred");
	}

	@Test
	public void fluxTestElementsCount_withError() {
		Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
				.concatWith(Flux.error(new RuntimeException("Exception Occurred"))).log();

		StepVerifier.create(stringFlux).expectNextCount(3).verifyErrorMessage("Exception Occurred");
	}

	@Test
	public void monoTest() {
		Mono<String> stringMono = Mono.just("Spring").log();

		StepVerifier.create(stringMono).expectNext("Spring").verifyComplete();
	}

	@Test
	public void monoTest_Error() {

		StepVerifier.create(Mono.error(new RuntimeException("Exception Occurred")))
				.verifyErrorMessage("Exception Occurred");

	}

}
