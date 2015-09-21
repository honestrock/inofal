package com.mert.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mert.model.User;

public class UserModelRowMapper implements RowMapper<User> {

	
	/**
	 * SQL USER TABLE  , colomn name varchar , ID varchar
	 * 
	 * @author Mert Kaya
	 */
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		User  model = new User();
		model.setId(rs.getString("ID"));
		model.setFirstName(rs.getString("name"));
		
		return model;
	}

}
