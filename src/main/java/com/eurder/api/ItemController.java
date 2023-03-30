package com.eurder.api;

import com.eurder.domain.user.Feature;
import com.eurder.dto.item.ItemDTO;
import com.eurder.dto.item.ItemOverviewDTO;
import com.eurder.security.SecurityService;
import com.eurder.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

	private final ItemService itemService;
	private final SecurityService securityService;

	public ItemController(ItemService itemService, SecurityService securityService) {
		this.securityService = securityService;
		this.itemService = itemService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ItemDTO addItem(@RequestHeader String authorization, @RequestBody ItemDTO itemDTO) {
		securityService.validateAuthorization(authorization, Feature.CREATE_ITEM);
		return itemService.addItem(itemDTO);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(consumes = "application/json", produces = "application/json")
	public ItemDTO updateItem(@RequestHeader String authorization, @RequestBody ItemDTO itemDTO) {
		securityService.validateAuthorization(authorization, Feature.UPDATE_ITEM);
		return itemService.updateItem(itemDTO);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = "application/json")
	public List<ItemDTO> getAllItems() {
		return itemService.getAllItems();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/overview", produces = "application/json")
	public List<ItemOverviewDTO> getAllItemsOverview(@RequestHeader String authorization) {
		securityService.validateAuthorization(authorization, Feature.VIEW_STOCK_OVERVIEW);
		return itemService.getAllItemsOverview();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/overview/lowstock", produces = "application/json")
	public ItemOverviewDTO getLowStockItemsOverview(@RequestHeader String authorization) {
		securityService.validateAuthorization(authorization, Feature.VIEW_STOCK_OVERVIEW);
		return itemService.getLowStockItemsOverview();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/overview/mediumstock", produces = "application/json")
	public ItemOverviewDTO getMediumStockItemsOverview(@RequestHeader String authorization) {
		securityService.validateAuthorization(authorization, Feature.VIEW_STOCK_OVERVIEW);
		return itemService.getMediumStockItemsOverview();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/overview/highstock", produces = "application/json")
	public ItemOverviewDTO getHighStockItemsOverview(@RequestHeader String authorization) {
		securityService.validateAuthorization(authorization, Feature.VIEW_STOCK_OVERVIEW);
		return itemService.getMediumStockItemsOverview();
	}

}
