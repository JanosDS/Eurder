package com.eurder.service;

import com.eurder.dto.user.CreateUserDTO;
import com.eurder.dto.user.UserDTO;
import com.eurder.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserMapper userMapper;

	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public UserDTO createCustomer(CreateUserDTO createUserDTO) {
		//TODO vallidation
		return userMapper.mapToDTO(userMapper.mapCreateUserDTOToDomain(createUserDTO));
	}
}
