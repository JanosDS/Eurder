package com.eurder.mapper.user;

import com.eurder.domain.user.User;
import com.eurder.dto.user.CreateUserDTO;
import com.eurder.dto.user.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

	private final AddressMapper addressMapper;

	public UserMapper(AddressMapper addressMapper) {
		this.addressMapper = addressMapper;
	}

	public User mapCreateUserDTOToDomain(CreateUserDTO createUserDTO) {
		return new User(createUserDTO.getFirstname(), createUserDTO.getLastname(), createUserDTO.getEmail(),
				addressMapper.mapToDomain(createUserDTO.getAddressDTO()), createUserDTO.getPhonenumber(), createUserDTO.getRole());
	}

	public UserDTO mapToDTO(User user) {
		return new UserDTO(user.getUuid(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getAddress(), user.getPhonenumber(), user.getRole());
	}

	public List<UserDTO> mapToDTO(List<User> userList) {
		return userList.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}
}
