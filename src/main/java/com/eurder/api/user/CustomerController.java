package com.eurder.api.user;

import com.eurder.domain.user.Feature;
import com.eurder.dto.order.OrderReportDTO;
import com.eurder.dto.user.CreateUserDTO;
import com.eurder.dto.user.UserDTO;
import com.eurder.security.SecurityService;
import com.eurder.service.OrderService;
import com.eurder.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	private final UserService userService;
	private final OrderService orderService;
	private final SecurityService securityService;

	public CustomerController(UserService userService, OrderService orderService, SecurityService securityService) {
		this.orderService = orderService;
		this.userService = userService;
		this.securityService = securityService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public UserDTO createCustomer(@RequestBody CreateUserDTO createUserDTO) {
		return userService.createCustomer(createUserDTO);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/{uuid}/orderReport", produces = "application/json")
	public OrderReportDTO getOrderReport(@PathVariable UUID uuid) {
		return orderService.getAllOrdersForUserId(uuid);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = "application/json")
	public List<UserDTO> getAllCustomers(@RequestHeader String authorization) {
		securityService.validateAuthorization(authorization, Feature.VIEW_ALL_CUSTOMERS);
		return userService.getAllCustomers();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "{uuid}", produces = "application/json")
	public UserDTO getCustomer(@RequestHeader String authorization, @PathVariable UUID uuid) {
		securityService.validateAuthorization(authorization, Feature.VIEW_CUSTOMER);
		return userService.getCustomer(uuid);
	}
}
