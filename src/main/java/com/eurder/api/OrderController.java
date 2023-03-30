package com.eurder.api;

import com.eurder.domain.user.Feature;
import com.eurder.dto.order.OrderDTO;
import com.eurder.dto.order.ShippingDTO;
import com.eurder.security.SecurityService;
import com.eurder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;
	private final SecurityService securityService;

	public OrderController(OrderService orderService, SecurityService securityService) {
		this.orderService = orderService;
		this.securityService = securityService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public OrderDTO addItem(@RequestHeader String authorization, @RequestBody OrderDTO orderDTO) {
		securityService.validateAuthorization(authorization, Feature.CREATE_ORDER);
		return orderService.createOrder(orderDTO);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "{orderid}", produces = "application/json")
	public OrderDTO addItem(@RequestHeader String authorization, @PathVariable UUID orderid) {
		securityService.validateAuthorization(authorization, Feature.CREATE_ORDER);
		return orderService.reorder(orderid);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/shiptoday", produces = "application/json")
	public List<ShippingDTO> getShipmentsForToday() {
		return orderService.getShipmentsForToday();
	}

}
