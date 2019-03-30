package com.learnreactivespring.fluxandmonoplaygound;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoWithTimeTest {
	
	@Test
	public void infiniteSequence() throws InterruptedException {
		Flux<Long> infiniteFlux = Flux.interval(Duration.ofMillis(10));
		
		infiniteFlux
			.subscribe(System.out::println);
		
		Thread.sleep(300);
	}
	
	@Test
	public void infiniteSequenceTest() throws InterruptedException {
		Flux<Long> finiteFlux = Flux.interval(Duration.ofMillis(10))
				.take(3);
		
		StepVerifier.create(finiteFlux)
			.expectSubscription()
			.expectNext(0L,1L,2L)
			.expectComplete();
	}

	@Test
	public void infiniteSequenceMap() throws InterruptedException {
		Flux<Integer> finiteFlux = Flux.interval(Duration.ofMillis(10))
				.map(l -> new Integer(l.intValue()))
				.take(3);
		
		StepVerifier.create(finiteFlux)
		.expectSubscription()
		.expectNext(0,1,2)
		.expectComplete();
	}

	@Test
	public void infiniteSequenceMap_withDelay() throws InterruptedException {
		Flux<Integer> finiteFlux = Flux.interval(Duration.ofMillis(10))
				.delayElements(Duration.ofMillis(1000))
				.map(l -> new Integer(l.intValue()))
				.take(3);
		
		StepVerifier.create(finiteFlux)
		.expectSubscription()
		.expectNext(0,1,2)
		.expectComplete();
	}

}
