package com.eurder.dto.user;



import java.util.UUID;

public class CreateUserDTO {

	private String firstname;
	private String lastname;
	private String email;
	private AddressDTO addressDTO;
	private String phonenumber;

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
}
