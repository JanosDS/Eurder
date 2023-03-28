package com.eurder.api.user;

import com.eurder.dto.user.CreateUserDTO;
import com.eurder.dto.user.UserDTO;
import com.eurder.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private final UserService userService;

	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public UserDTO createAdmin(@RequestBody CreateUserDTO createUserDTO){
		//TODO security
		return userService.createAdmin(createUserDTO);
	}
}
