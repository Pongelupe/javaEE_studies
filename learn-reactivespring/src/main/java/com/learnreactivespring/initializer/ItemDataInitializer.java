package com.learnreactivespring.initializer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learnreactivespring.document.Item;
import com.learnreactivespring.repository.ItemReactiveRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class ItemDataInitializer implements CommandLineRunner {

	@Autowired
	private ItemReactiveRepository itemReactiveRepository;

	@Override
	public void run(String... args) throws Exception {
		itemReactiveRepository.deleteAll().thenMany(Flux.fromIterable(data()))
			.flatMap(itemReactiveRepository::save)
			.thenMany(itemReactiveRepository.findAll())
			.subscribe(item -> log.info(item.toString()));
	}

	private List<Item> data() {
		return Arrays.asList(new Item(null, "Samsung Tv", 255.0), new Item(null, "LG Tv", 252.0),
				new Item(null, "Apple Tv", 252.0), new Item(null, "Bla Tv", 252.0),
				new Item("fakeid", "bullshit", 252.0));
	}

}
