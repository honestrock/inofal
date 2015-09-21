package com.mert.implementation.jdbc;

import java.sql.SQLException;
import java.util.List;
/**
 * 
 * @author Mert Kaya
 *
 */
public interface IProcedureExecutor {
	Object execProc(ProcedureDefinition procedureDefinition, Object[] args)
			throws SQLException;

	List execProc1(ProcedureDefinition procedureDefinition, Object[] args)
			throws SQLException;
}
