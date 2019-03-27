package com.learnreactivespring.fluxandmonoplaygound;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoTransformTest {

	List<String> names = Arrays.asList("adam", "anna", "jack", "jenny");

	@Test
	public void filterTest() {
		Flux<String> namesFlux = Flux.fromIterable(names).filter(s -> s.startsWith("a"));

		StepVerifier.create(namesFlux).expectNext("adam", "anna").verifyComplete();
	}

	@Test
	public void filterTestLength() {
		Flux<String> namesFlux = Flux.fromIterable(names).filter(s -> s.length() > 4);

		StepVerifier.create(namesFlux).expectNext("jenny").verifyComplete();
	}

	@Test
	public void transformUsingFlatMap() {
		Flux<String> stringFlux = Flux.fromIterable(Arrays.asList("A", "B", "C", "D", "E", "F"))
				.flatMap(s -> Flux.fromIterable(convertToList(s))).log();

		StepVerifier.create(stringFlux).expectNextCount(12).verifyComplete();
	}

	private List<String> convertToList(String s) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		return Arrays.asList(s, "new value");
	}
	

}
