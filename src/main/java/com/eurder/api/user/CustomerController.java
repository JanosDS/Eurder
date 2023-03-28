package com.eurder.api.user;

import com.eurder.dto.user.CreateUserDTO;
import com.eurder.dto.user.UserDTO;
import com.eurder.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private final UserService userService;

	public CustomerController(UserService userService) {
		this.userService = userService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public UserDTO createCustomer(@RequestBody CreateUserDTO createUserDTO){
		return userService.createCustomer(createUserDTO);
	}

}
