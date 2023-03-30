package com.eurder.dto.user;



import com.eurder.domain.user.Role;

import java.util.UUID;

public class CreateUserDTO {

	private final String firstname;
	private final String lastname;
	private final String email;
	private final AddressDTO addressDTO;
	private final String phonenumber;

	private Role role;
	public CreateUserDTO(String firstname, String lastname, String email, AddressDTO addressDTO, String phonenumber) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.addressDTO = addressDTO;
		this.phonenumber = phonenumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public CreateUserDTO setRole(Role role) {
		this.role = role;
		return this;
	}

	public Role getRole() {
		return role;
	}
}
