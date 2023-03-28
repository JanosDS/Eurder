package com.eurder.domain.user;

import com.eurder.dto.user.CreateUserDTO;

public enum Role {
	CUSTOMER;


	public static CreateUserDTO setRoleToCustomer(CreateUserDTO user){
		return user.setRole(CUSTOMER);
	}
}
