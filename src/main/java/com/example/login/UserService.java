package com.example.login;

import org.springframework.stereotype.Service;

@Service
public interface UserService{
	
	void registerUser(User user);
	User getUserByUsername(String username);

}
