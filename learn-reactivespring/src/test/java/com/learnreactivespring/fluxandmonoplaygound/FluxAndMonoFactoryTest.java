package com.learnreactivespring.fluxandmonoplaygound;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoFactoryTest {

	List<String> names = Arrays.asList("adam", "anna", "jack", "jenny");

	@Test
	public void fluxUsingIterable() {
		Flux<String> namesFlux = Flux.fromIterable(names).log();

		StepVerifier.create(namesFlux).expectNext("adam", "anna", "jack", "jenny").verifyComplete();
	}

	@Test
	public void fluxUsingArray() {
		Flux<String> namesFlux = Flux.fromArray(new String[] { "adam", "anna", "jack", "jenny" }).log();

		StepVerifier.create(namesFlux).expectNext("adam", "anna", "jack", "jenny").verifyComplete();
	}

	@Test
	public void fluxUsingStream() {
		Flux<String> namesFlux = Flux.fromStream(names.stream()).log();

		StepVerifier.create(namesFlux).expectNext("adam", "anna", "jack", "jenny").verifyComplete();
	}

	@Test
	public void fluxUsingRange() {
		Flux<Integer> rangeFlux = Flux.range(1, 5);

		StepVerifier.create(rangeFlux).expectNext(1, 2, 3, 4, 5).verifyComplete();
	}

	@Test
	public void monoUsingJustOrEmpty() {
		Mono<String> mono = Mono.justOrEmpty(null);

		StepVerifier.create(mono).verifyComplete();
	}

	@Test
	public void monoUsingSupplier() {
		Mono<String> mono = Mono.fromSupplier(() -> "adam");

		StepVerifier.create(mono).expectNext("adam").verifyComplete();
	}

}
