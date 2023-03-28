package com.eurder.domain.user;

import com.eurder.dto.user.CreateUserDTO;

public enum Role {
	CUSTOMER,
	ADMIN;


	public static CreateUserDTO setRoleToCustomer(CreateUserDTO user){
		return user.setRole(CUSTOMER);
	}
	public static CreateUserDTO setRoleToAdmin(CreateUserDTO user){
		return user.setRole(ADMIN);
	}
}
