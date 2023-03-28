package com.eurder.repository;

import com.eurder.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {

	private List<User> userList;

	public UserRepository() {
		this.userList = new ArrayList<>();

	}

	public List<User> getUserList() {
		return userList;
	}

	public User addUser(User user){
		userList.add(user);
		return user;
	}

	public Optional<User> getUserByUuid(UUID uuid){
		return userList.stream()
				.filter(user -> user.getUuid().equals(uuid))
				.findFirst();
	}
}
