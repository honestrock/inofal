package com.mert.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.mert.service.IDatabaseService;

public class ImpDao implements IDao{
	
	@Autowired
	private IDatabaseService databaseService;

	public UserListDto getAllUsers() {
		// TODO Auto-generated method stub
		UserListDto  userList = new UserListDto();
		userList.setUsers(databaseService.getAllUsers(1));
		return userList ;
	}

}
