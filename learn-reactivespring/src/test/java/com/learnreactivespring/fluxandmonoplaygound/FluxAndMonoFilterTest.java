package com.learnreactivespring.fluxandmonoplaygound;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoFilterTest {

	List<String> names = Arrays.asList("adam", "anna", "jack", "jenny");

	@Test
	public void transformUsingMapTest() {
		Flux<String> namesFlux = Flux.fromIterable(names).map(String::toUpperCase);

		StepVerifier.create(namesFlux).expectNext("ADAM", "ANNA", "JACK", "JENNY").verifyComplete();
	}

	@Test
	public void transformUsingMapTest_Length() {
		Flux<Integer> namesFlux = Flux.fromIterable(names).map(String::length);

		StepVerifier.create(namesFlux).expectNext(4, 4, 4, 5).verifyComplete();
	}

	@Test
	public void transformUsingMapFilterTest() {
		Flux<String> namesFlux = Flux.fromIterable(names).map(String::toUpperCase).filter(s -> s.startsWith("A"));

		StepVerifier.create(namesFlux).expectNext("ADAM", "ANNA").verifyComplete();
	}

}
