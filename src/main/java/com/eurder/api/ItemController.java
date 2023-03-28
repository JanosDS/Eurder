package com.eurder.api;

import com.eurder.dto.item.CreateItemDTO;
import com.eurder.dto.item.ItemDTO;
import com.eurder.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ItemDTO addItem(@RequestBody CreateItemDTO createItemDTO){
		//TODO add security
		return itemService.addItem(createItemDTO);
	}
}
