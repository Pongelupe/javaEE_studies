package com.learnreactivespring.fluxandmonoplaygound;

import org.junit.Test;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoBackPressureTest {
	
	@Test
	public void backPressureTest() {
		Flux<Integer> finiteFlux = Flux.range(1, 10);
		
		StepVerifier.create(finiteFlux)
			.expectSubscription()
			.thenRequest(1)
			.expectNext(1)
			.thenRequest(1)
			.expectNext(2)
			.thenRequest(1)
			.expectNext(3)
			.thenCancel()
			.verify();
	}
	
	@Test
	public void backPressure() {
		Flux<Integer> finiteFlux = Flux.range(1, 10);
		
		finiteFlux
			.subscribe(
					System.out::println, 
					System.err::println,
					() -> System.out.println("the end"),
					subscription -> subscription.request(2));
	}

	@Test
	public void backPressure_cancel() {
		Flux<Integer> finiteFlux = Flux.range(1, 10);
		
		finiteFlux
			.subscribe(
					System.out::println, 
					System.err::println,
					() -> System.out.println("the end"),
					subscription -> subscription.cancel());
	}

	@Test
	public void custom_backPressure() {
		Flux<Integer> finiteFlux = Flux.range(1, 10);
		
		finiteFlux
			.subscribe(new BaseSubscriber<Integer>() {
				@Override
				protected void hookOnNext(Integer value) {
					request(1);
					System.out.println(value);
					if (value == 4) {
						cancel();
					}
				}
			});
	}

}
