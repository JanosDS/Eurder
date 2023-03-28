package com.eurder.api;

import com.eurder.domain.user.Feature;
import com.eurder.dto.item.CreateItemDTO;
import com.eurder.dto.item.ItemDTO;
import com.eurder.security.SecurityService;
import com.eurder.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

	private final ItemService itemService;
	private final SecurityService securityService;

	public ItemController(ItemService itemService, SecurityService securityService) {
		this.securityService = securityService;
		this.itemService = itemService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ItemDTO addItem(@RequestHeader String authorization, @RequestBody CreateItemDTO createItemDTO){
		securityService.validateAuthorization(authorization, Feature.CREATE_ITEM);
		return itemService.addItem(createItemDTO);
	}
}
