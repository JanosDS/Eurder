package com.eurder.api.user;

import com.eurder.domain.user.Feature;
import com.eurder.dto.user.CreateUserDTO;
import com.eurder.dto.user.UserDTO;
import com.eurder.security.SecurityService;
import com.eurder.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private final UserService userService;
	private final SecurityService securityService;

	public AdminController(UserService userService, SecurityService securityService) {
		this.securityService = securityService;
		this.userService = userService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public UserDTO createAdmin(@RequestHeader String authorization, @RequestBody CreateUserDTO createUserDTO){
		securityService.validateAuthorization(authorization, Feature.CREATE_ADMIN);
		return userService.createAdmin(createUserDTO);
	}
}
