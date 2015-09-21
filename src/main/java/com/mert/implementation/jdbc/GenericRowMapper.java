package com.mert.implementation.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Mert Kaya
 *
 */
public class GenericRowMapper implements RowMapper<Object> {
	protected Logger LOG = Logger.getLogger(this.getClass());

	private static String getJavaType(int sqlTypeId) {

		switch (sqlTypeId) {
		case Types.VARCHAR:
			return "String";

		case Types.DATE:
			return "Date";

		case Types.NUMERIC:
			return "int";

		}

		return "" + sqlTypeId;
	}

	public static void showRow(ResultSet rset, int rowNo) throws SQLException {

		ResultSetMetaData metaData = rset.getMetaData();
		int col = metaData.getColumnCount();
		for (int i = 1; i <= col; i++) {
			String columnName = metaData.getColumnName(i).toLowerCase(
					Locale.ENGLISH);
			String columnTypeName = getJavaType(metaData.getColumnType(i));
			Object object = rset.getObject(i);

			StringBuilder buf = new StringBuilder();
			boolean upperCase = false;
			for (int j = 0; j < columnName.length(); j++) {
				if (columnName.charAt(j) == '_') {
					upperCase = true;
					continue;
				}

				if (upperCase) {
					buf.append(Character.toUpperCase(columnName.charAt(j)));
					upperCase = false;
				} else
					buf.append(columnName.charAt(j));

			}

			System.out.println(" private " + columnTypeName + " " + buf
					+ "; // " + object);
		}
	}

	public Object mapRow(ResultSet rset, int rowNo) throws SQLException {
		showRow(rset, rowNo);
		return null;
	}

	
}
