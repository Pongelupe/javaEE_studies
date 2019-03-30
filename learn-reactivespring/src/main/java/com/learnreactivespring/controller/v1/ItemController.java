package com.learnreactivespring.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnreactivespring.constants.ItemConstants;
import com.learnreactivespring.document.Item;
import com.learnreactivespring.repository.ItemReactiveRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class ItemController {
	
	@Autowired
	ItemReactiveRepository itemRepository;
	
	@GetMapping(ItemConstants.ITEM_END_POINT_V1)
	public Flux<Item> getAllItems() {
		return itemRepository.findAll();
	}

}
