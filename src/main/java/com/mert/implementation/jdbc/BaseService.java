package com.mert.implementation.jdbc;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;

import com.mert.exception.ServiceException;




/**
 * 
 * @author Mert Kaya
 *
 */
@SuppressWarnings("rawtypes")
public abstract class BaseService implements InitializingBean  {

	private String schemaName;
	private IProcedureExecutor procExecutor;

	private final Logger logger =
		        Logger.getLogger(BaseService.class.getName());


	

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	protected Object executeProcedureSingle(ProcedureDefinition definition,
			Object... args) {

		List list = executeProcedure(definition, args);

		if (list != null && list.size() == 1) {
			return list.get(0);
		}

		return null;
	}

	protected Integer executeProcedureInt(ProcedureDefinition definition,
			Object... args) {
		try {
			if (definition == null)
				throw new ServiceException(20000);

			Integer value = (Integer) procExecutor.execProc(definition, args);
			return value;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(sqle.getErrorCode());
		}
	}

	protected double executeProcedureDouble(ProcedureDefinition definition,
			Object... args) {
		try {
			if (definition == null)
				throw new ServiceException(20000);

			Object value = procExecutor.execProc(definition, args);

			if (value instanceof Double)
				return ((Double) value).doubleValue();

			if (value instanceof Integer)
				return ((Integer) value).doubleValue();

			return Double.parseDouble(value.toString());

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(sqle.getErrorCode());
		}
	}

	protected Boolean executeProcedureBoolean(ProcedureDefinition definition,
			Object... args) {
		try {
			if (definition == null)
				throw new ServiceException(20000);

			Integer value = (Integer) procExecutor.execProc(definition, args);

			if (value.intValue() > 0)
				return Boolean.TRUE;

			return Boolean.FALSE;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(sqle.getErrorCode());
		}
	}

	protected String executeProcedureString(ProcedureDefinition definition,
			Object... args) {
		try {
			if (definition == null)
				throw new ServiceException(20000);

			String value = (String) procExecutor.execProc(definition, args);
			return value;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(sqle.getErrorCode());
		}
	}

	protected Date executeProcedureDate(ProcedureDefinition definition,
			Object... args) {
		try {
			if (definition == null)
				throw new ServiceException(20000);

			Date value = (Date) procExecutor.execProc(definition, args);
			return value;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(sqle.getErrorCode());
		}
	}

	protected void executeProcedureNoReturn(ProcedureDefinition definition,
			Object... args) {
		try {
			if (definition == null)
				throw new ServiceException(20000);

			procExecutor.execProc(definition, args);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ServiceException(sqle.getErrorCode());
		}
	}

	protected List executeProcedure(ProcedureDefinition definition,
			Object... args) {
		try {
			if (definition == null)
				throw new ServiceException(20000);

			return (List) procExecutor.execProc(definition, args);

		} catch (SQLException sqle) {
			// sqle.printStackTrace();
			throw new ServiceException(sqle.getErrorCode());
		}
	}

	public IProcedureExecutor getProcExecutor() {
		return procExecutor;
	}

	public void setProcExecutor(IProcedureExecutor procExecutor) {
		this.procExecutor = procExecutor;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public Logger getLogger() {
		return logger;
	}
	
	

}
