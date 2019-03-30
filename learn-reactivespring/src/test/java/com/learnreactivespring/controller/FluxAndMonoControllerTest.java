package com.learnreactivespring.controller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest
@AutoConfigureWebTestClient(timeout = "36000")
public class FluxAndMonoControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@Test
	public void flux_approach1() {
		Flux<Integer> integerFlux = webTestClient.get().uri("/flux").accept(MediaType.APPLICATION_JSON_UTF8).exchange()
				.expectStatus().isOk().returnResult(Integer.class).getResponseBody();

		StepVerifier.create(integerFlux).expectSubscription().expectNext(1, 2, 3, 4).verifyComplete();
	}

	@Test
	public void flux_approach2() {
		webTestClient.get().uri("/flux").accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8).expectBodyList(Integer.class).hasSize(4);
	}

	@Test
	public void flux_approach3() {
		List<Integer> expectedList = Arrays.asList(1, 2, 3, 4);

		EntityExchangeResult<List<Integer>> exchangeResult = webTestClient.get().uri("/flux")
				.accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus().isOk().expectHeader()
				.contentType(MediaType.APPLICATION_JSON_UTF8).expectBodyList(Integer.class).returnResult();

		assertEquals(expectedList, exchangeResult.getResponseBody());
	}

	@Test
	public void flux_approach4() {
		List<Integer> expectedList = Arrays.asList(1, 2, 3, 4);

		webTestClient.get().uri("/flux").accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8).expectBodyList(Integer.class)
				.consumeWith(response -> assertEquals(expectedList, response.getResponseBody()));

	}

	@Test
	public void fluxStream() {
		Flux<Long> longStreamFlux = webTestClient.get().uri("/fluxstream").accept(MediaType.APPLICATION_STREAM_JSON)
				.exchange().expectStatus().isOk().returnResult(Long.class).getResponseBody();

		StepVerifier.create(longStreamFlux).expectNext(0L).expectNext(1L).expectNext(2L).thenCancel().verify();
	}

	@Test
	public void mono() {
		Integer expected = new Integer(1);
		webTestClient.get().uri("/mono").accept(MediaType.APPLICATION_JSON_UTF8).exchange().expectStatus().isOk()
				.expectBody(Integer.class).consumeWith(res -> assertEquals(expected, res.getResponseBody()));
	}

}
