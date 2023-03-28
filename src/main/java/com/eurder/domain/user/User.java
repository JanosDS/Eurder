package com.eurder.domain.user;

import java.util.UUID;

public class User {
	private UUID uuid;
	private String firstname;
	private String lastname;
	private String email;
	private Address address;
	private String phonenumber;
	private Role role;

	public User(String firstname, String lastname, String email, Address address, String phonenumber, Role role) {
		this.uuid = UUID.randomUUID();
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
