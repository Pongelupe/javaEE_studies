package com.learnreactivespring.fluxandmonoplaygound;

import java.time.Duration;

import org.junit.Test;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ColdAndHotPublisherTest {

	@Test
	public void coldPublisherTest() throws InterruptedException {
		Flux<String> flux = Flux.just("A", "B", "C", "D", "E", "F")
					.delayElements(Duration.ofSeconds(1));
		
		flux.subscribe(s -> System.out.println(1 + ": " + s)); // emits the value from beginning

		Thread.sleep(2000);
		
		flux.subscribe(s -> System.out.println(2 + ": " + s)); // emits the value from beginning

		Thread.sleep(4000);
	}
	
	@Test
	public void hotPublisherTest() throws InterruptedException {
		Flux<String> flux = Flux.just("A", "B", "C", "D", "E", "F")
				.delayElements(Duration.ofSeconds(1));
		
		ConnectableFlux<String> connectableFlux = flux.publish();
		connectableFlux.connect();
		
		
		connectableFlux.subscribe(s -> System.out.println(1 + ": " + s)); // emits the value from beginning
		
		Thread.sleep(3000);
		
		connectableFlux.subscribe(s -> System.out.println(2 + ": " + s)); // emits the value from beginning
		
		Thread.sleep(4000);
	}

}
