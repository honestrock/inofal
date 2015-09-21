package com.mert.service;

import java.util.List;

import com.mert.model.User;

public interface IDatabaseService {

	List<User> getAllUsers(int status);
	
}
