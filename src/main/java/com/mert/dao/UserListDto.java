package com.mert.dao;

import java.util.List;

import com.mert.model.User;



public class UserListDto {

	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}