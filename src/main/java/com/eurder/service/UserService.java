package com.eurder.service;

import com.eurder.domain.user.Role;
import com.eurder.dto.user.AddressDTO;
import com.eurder.dto.user.CreateUserDTO;
import com.eurder.dto.user.UserDTO;
import com.eurder.exception.InvalidInputException;
import com.eurder.exception.MandatoryFieldException;
import com.eurder.mapper.user.UserMapper;
import com.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {

	private final UserMapper userMapper;
	private final UserRepository userRepository;

	public UserService(UserMapper userMapper, UserRepository userRepository) {
		this.userMapper = userMapper;
		this.userRepository = userRepository;
	}

	public UserDTO createCustomer(CreateUserDTO createUserDTO) {
		validateMandatoryCustomerFields(createUserDTO);
		validateAddress(createUserDTO.getAddressDTO());
		validateEmail(createUserDTO.getEmail());

		return userMapper.mapToDTO(
				userRepository.addUser(
						userMapper.mapCreateUserDTOToDomain(Role.setRoleToCustomer(createUserDTO))));
	}

	public UserDTO createAdmin(CreateUserDTO createUserDTO) {
		validateMandatoryCustomerFields(createUserDTO);
		validateAddress(createUserDTO.getAddressDTO());
		validateEmail(createUserDTO.getEmail());

		return userMapper.mapToDTO(
				userRepository.addUser(
						userMapper.mapCreateUserDTOToDomain(Role.setRoleToAdmin(createUserDTO))));
	}

	private void validateEmail(String email) {
		if (!isValidEmailFormat(email)) {
			throw new InvalidInputException(email + " is not a valid email format.");
		}
	}

	public void validateMandatoryCustomerFields(CreateUserDTO createUserDTO) {
		if (createUserDTO.getFirstname() == null) {
			throw new MandatoryFieldException("The firstname field cannot be empty.");
		}
		if (createUserDTO.getLastname() == null) {
			throw new MandatoryFieldException("The lastname field cannot be empty.");
		}
		if (createUserDTO.getEmail() == null) {
			throw new MandatoryFieldException("The email field cannot be empty.");
		}
		if (createUserDTO.getPhonenumber() == null) {
			throw new MandatoryFieldException("The phone number field cannot be empty.");
		}
		if (createUserDTO.getAddressDTO() == null) {
			throw new MandatoryFieldException("The address field cannot be empty.");
		}
	}

	public void validateAddress(AddressDTO addressDTO) {
		if (addressDTO.getCity() == null) {
			throw new MandatoryFieldException("The city field cannot be empty.");
		}
		if (addressDTO.getStreet() == null) {
			throw new MandatoryFieldException("The street field cannot be empty.");
		}
		if (addressDTO.getNumber() == null) {
			throw new MandatoryFieldException("The housenumber field cannot be empty.");
		}
		if (addressDTO.getCountry() == null) {
			throw new MandatoryFieldException("The country field cannot be empty.");
		}
		if (addressDTO.getPostalCode() == null) {
			throw new MandatoryFieldException("The postalcode field cannot be empty.");
		}
	}

	public boolean isValidEmailFormat(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null) {
			return false;
		}
		return pat.matcher(email).matches();
	}

	public List<UserDTO> getAllCustomers(){
		return userMapper.mapToDTO(userRepository.getUserList());
	}
}
