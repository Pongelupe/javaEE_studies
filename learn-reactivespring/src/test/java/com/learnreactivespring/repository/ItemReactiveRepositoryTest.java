package com.learnreactivespring.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.learnreactivespring.document.Item;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
@RunWith(SpringRunner.class)
@DirtiesContext
public class ItemReactiveRepositoryTest {

	@Autowired
	ItemReactiveRepository itemReactiveRepository;

	List<Item> items = Arrays.asList(new Item(null, "Samsung Tv", 255.0), new Item(null, "LG Tv", 252.0),
			new Item(null, "Apple Tv", 252.0), new Item(null, "Bla Tv", 252.0), new Item("fakeid", "bullshit", 252.0));

	@Before
	public void setUp() {
		itemReactiveRepository.deleteAll().thenMany(Flux.fromIterable(items)).flatMap(itemReactiveRepository::save)
				.doOnNext(System.out::println).blockLast();
	}

	@Test
	public void getAllItens() {
		StepVerifier.create(itemReactiveRepository.findAll()).expectSubscription().expectNextCount(5).verifyComplete();
	}

	@Test
	public void getById() {
		StepVerifier.create(itemReactiveRepository.findById("fakeid")).expectSubscription().expectNextMatches(
				i -> i.getId().equals("fakeid") && i.getDescription().equals("bullshit") && i.getPrice().equals(252.0))
				.verifyComplete();
	}
	
	@Test
	public void getByDescription() {
		StepVerifier.create(itemReactiveRepository.findByDescription("bullshit")).expectSubscription().expectNextMatches(
				i -> i.getId().equals("fakeid") && i.getDescription().equals("bullshit") && i.getPrice().equals(252.0))
				.verifyComplete();
	}
	
	@Test
	public void saveItem() {
		Item item = new Item(null, "Google Home mini", 30.0);
		
		StepVerifier.create(itemReactiveRepository.save(item))
			.expectSubscription()
			.expectNextMatches(i -> i.getId() != null && i.getDescription().equals("Google Home mini") && i.getPrice().equals(30.0))
			.verifyComplete();
	}
	
	@Test
	public void updateItem() {
		Item item = new Item("fakeid", "Google Home mini", 30.0);
		
		StepVerifier.create(itemReactiveRepository.save(item))
		.expectSubscription()
		.expectNextMatches(i -> i.getId().equals("fakeid") && i.getDescription().equals("Google Home mini") && i.getPrice().equals(30.0))
		.verifyComplete();
	}
	
	@Test
	public void deleteItem() {
		StepVerifier.create(itemReactiveRepository.findByDescription("Bla Tv"))
		.expectSubscription()
		.expectNextCount(1)
		.verifyComplete();
		
		Flux<Void> deletedItem = itemReactiveRepository.findByDescription("Bla Tv")
			.map(Item::getId)
			.flatMap(itemReactiveRepository::deleteById);
		
		StepVerifier.create(deletedItem)
			.expectSubscription()
			.verifyComplete();
		
		StepVerifier.create(itemReactiveRepository.findByDescription("Bla Tv"))
			.expectSubscription()
			.expectNextCount(0)
			.verifyComplete();
	}

}
