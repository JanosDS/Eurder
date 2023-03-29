package com.eurder.api.user;

import com.eurder.domain.order.Order;
import com.eurder.dto.order.OrderReportDTO;
import com.eurder.dto.user.CreateUserDTO;
import com.eurder.dto.user.UserDTO;
import com.eurder.service.OrderService;
import com.eurder.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private final UserService userService;
	private final OrderService orderService;

	public CustomerController(UserService userService, OrderService orderService) {
		this.orderService = orderService;
		this.userService = userService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public UserDTO createCustomer(@RequestBody CreateUserDTO createUserDTO){
		return userService.createCustomer(createUserDTO);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/{uuid}/orderReport",produces = "application/json")
	public OrderReportDTO getOrderReport(@PathVariable UUID uuid){
		return orderService.getAllOrdersForUserId(uuid);
	}

}
