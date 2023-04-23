package com.lcwd.user.service.services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService {
	
	//create
	User saveUser(User user);
	 
	
	//get all user
	List<User> getAllUser();
	
	//get single user
	User getUser(String userId);

}
