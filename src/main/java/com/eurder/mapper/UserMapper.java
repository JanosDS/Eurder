package com.eurder.mapper;

import com.eurder.domain.user.User;
import com.eurder.dto.user.CreateUserDTO;
import com.eurder.dto.user.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	private final AddressMapper addressMapper;

	public UserMapper(AddressMapper addressMapper) {
		this.addressMapper = addressMapper;
	}

	public User mapCreateUserDTOToDomain(CreateUserDTO createUserDTO){
		return new User(createUserDTO.getFirstname(), createUserDTO.getLastname(), createUserDTO.getEmail(), addressMapper.mapToDomain(createUserDTO.getAddressDTO()), createUserDTO.getPhonenumber());
	}

	public UserDTO mapToDTO(User user){
		return new UserDTO();
	}
}
