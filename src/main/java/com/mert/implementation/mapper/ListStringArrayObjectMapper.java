package com.mert.implementation.mapper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//import oracle.sql.ARRAY;
//import oracle.sql.ArrayDescriptor;



import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;

import com.mert.implementation.jdbc.IObjectMapper;


/**
 * 
 * @author Mert Kaya
 *
 */
@SuppressWarnings("rawtypes")
public class ListStringArrayObjectMapper implements IObjectMapper {
	private String schemaName = "TICKET";
	private String sqlObjectNumberArrayTypeName = schemaName
			+ ".COLLECT_STRING_T";



	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public void mapParameter(JdbcTemplate jdbcTemplate, Connection connection,
			CallableStatement call, int curNo, Object object)
			throws SQLException {
		// TODO Auto-generated method stub
		
		List list = (List) object;
		Connection nativeConnection = null;

		NativeJdbcExtractor nativeJdbcExtractor = jdbcTemplate
				.getNativeJdbcExtractor();
		nativeConnection = nativeJdbcExtractor.getNativeConnection(connection);

		int size = 0;
		if (list != null) {
			size = list.size();
		}
		String ids[] = new String[size];
		for (int i = 0; i < ids.length; i++)
			ids[i] = (String) list.get(i);



		
	}

}
