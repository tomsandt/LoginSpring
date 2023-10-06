package com.example.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	private final List<User> users = new ArrayList<>();
	

	@Override
	public void registerUser(User user) {
	
		users.add(user)
;		
	}
	
	@Override
	public User getUserByUsername(String username) {
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

}
