package com.mert.implementation.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;

/**
 * 
 * @author Mert Kaya
 * 
 */
@SuppressWarnings("unchecked")
public class MySqlProcedureExecutor implements IProcedureExecutor {

	private Logger LOG = Logger.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplate;
	private String schemaName;
	@SuppressWarnings("rawtypes")
	private Map<String, IObjectMapper> objmapper;
	private Gson gson;

	public List execProc1(ProcedureDefinition procedureDefinition, Object[] args)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;

	}

	public Object execProc(ProcedureDefinition procedureDefinition,
			Object[] args) throws SQLException {

		DataSource dataSource;

		checkDefinition(procedureDefinition, args);

		ResultSet rs = null;
		Connection connection = null;
		CallableStatement call = null;

		try {
			dataSource = jdbcTemplate.getDataSource();
			connection = dataSource.getConnection();
			call = connection.prepareCall(procedureDefinition.getCompiledSQL());

			int curNo = prepareCall(procedureDefinition, connection, call,
					jdbcTemplate, args);

			if (!procedureDefinition.isHasoutput()) {
				return call.executeUpdate();
			} else if (procedureDefinition.getRowMapper() != null) {
				try {
					rs = call.executeQuery();
					List<Object> objects = new ArrayList<Object>();
					int i = 1;
					while (rs.next()) {
						Object object = procedureDefinition.getRowMapper()
								.mapRow(rs, i);
						objects.add(object);
						i++;
					}
					return objects;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				int debug = call.executeUpdate();
				if (procedureDefinition.getOutputType() == Types.INTEGER) {
					int int1 = call.getInt(curNo - 1);
					return new Integer(int1);
				} else if (procedureDefinition.getOutputType() == Types.DOUBLE) {
					double int1 = call.getDouble(curNo - 1);
					return new Double(int1);
				} else if (procedureDefinition.getOutputType() == Types.VARCHAR) {
					String value = call.getString(curNo - 1);
					return value;
				} else if (procedureDefinition.getOutputType() == Types.DATE) {
					java.sql.Date value = call.getDate(curNo - 1);
					if (value == null)
						return null;

					return new Date(value.getTime());

				} else {
					LOG.error("OracleDebugProcedureExecutor::execProc Unprocessed return :"
							+ call.getObject(curNo));
				}
			}
		} catch (SQLException sqle) {
			close(call, connection);
			sqle.printStackTrace();
			throw sqle;
		} finally {
			close(call, connection);
		}

		return null;

	}

	private void checkDefinition(ProcedureDefinition procedureDefinition,
			Object[] args) throws SQLException {
		// TODO Auto-generated method stub
		if (procedureDefinition.getTypes() == null && args != null
				|| procedureDefinition.getTypes() != null && args == null) {
			LOG.error("::execProc :" + procedureDefinition.getName()
					+ " parameter mismatch 1");
			throw new SQLException("mismatch", "20020");

		}
		if (procedureDefinition.getTypes() != null
				&& (procedureDefinition.getTypes().length != args.length)) {
			LOG.error("::execProc " + procedureDefinition.getName()
					+ " parameter mismatch "
					+ procedureDefinition.getTypes().length + " !="
					+ args.length);
			throw new SQLException("mismatch", "20020");
		}
	}

	private void close(Statement statement, Connection connection) {

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {

			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {

			}
		}
	}

	@SuppressWarnings("rawtypes")
	private int prepareCall(ProcedureDefinition definition,
			Connection connection, CallableStatement call,
			JdbcTemplate jdbcTemplate2, Object[] args) throws SQLException {

		// call.setInt(1, userapp.getUserId());
		// call.setInt(2, userapp.getApplicationId());

		int curNo = 1;

		if (definition.getTypes() != null) {
			int types[] = definition.getTypes();

			for (int i = 0; i < types.length; i++) {

				if (args[i] == null && types[i] != Types.STRUCT) {
					call.setNull(curNo, types[i]);
				} else if (types[i] == Types.DATE) {
					if (args[i] instanceof Date)
						call.setTime(curNo,
								new java.sql.Time(((Date) args[i]).getTime()));
					else if (args[i] instanceof Integer)
						call.setTime(curNo, new java.sql.Time(
								((Integer) args[i]).intValue()));
					else if (args[i] instanceof Long)
						call.setTime(curNo,
								new java.sql.Time(((Long) args[i]).longValue()));
				} else if (types[i] == Types.INTEGER) {
					call.setObject(curNo, args[i]);
				} else if (types[i] == Types.FLOAT) {
					call.setObject(curNo, args[i]);
				} else if (types[i] == Types.VARCHAR) {
					call.setObject(curNo, args[i]);
				}

				curNo++;
			}
		}

		return curNo;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public Map<String, IObjectMapper> getObjmapper() {
		return objmapper;
	}

	public void setObjmapper(Map<String, IObjectMapper> objmapper) {
		this.objmapper = objmapper;
	}

	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

}
