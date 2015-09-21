package com.mert.repository.sql;

import java.sql.Types;
import java.util.List;

import com.mert.implementation.jdbc.BaseService;
import com.mert.implementation.jdbc.ProcedureDefinition;
import com.mert.model.User;
import com.mert.model.rowmapper.UserModelRowMapper;
import com.mert.service.IDatabaseService;

public class DatabaseService extends BaseService implements IDatabaseService {

	private ProcedureDefinition procGetAllUser;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		super.afterPropertiesSet();
		procGetAllUser = new ProcedureDefinition(getSchemaName(), "P_USER_ALL",
				new UserModelRowMapper(), Types.INTEGER);
	}

	public List<User> getAllUsers(int status) {
		// TODO Auto-generated method stub
		List<User> models = executeProcedure(procGetAllUser, status);
		return models;
	}

}
