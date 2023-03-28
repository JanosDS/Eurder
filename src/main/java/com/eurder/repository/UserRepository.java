package com.eurder.repository;

import com.eurder.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
}
