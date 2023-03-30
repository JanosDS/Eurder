package com.eurder.dto.user;

import com.eurder.domain.user.Address;
import com.eurder.domain.user.Role;

import java.util.UUID;

public class UserDTO {
	private final UUID uuid;
	private final String firstname;
	private final String lastname;
	private final String email;
	private final Address address;
	private final String phonenumber;
	private final Role role;

	public UserDTO(UUID uuid, String firstname, String lastname, String email, Address address, String phonenumber, Role role) {
		this.uuid = uuid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.phonenumber = phonenumber;
		this.role = role;
	}

	public UUID getUuid() {
		return uuid;
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

	public Address getAddress() {
		return address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public Role getRole() {
		return role;
	}
}
