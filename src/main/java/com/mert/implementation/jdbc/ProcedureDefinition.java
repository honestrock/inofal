package com.mert.implementation.jdbc;

import org.springframework.jdbc.core.RowMapper;



/**
 * 
 * @author Mert Kaya
 *
 */
@SuppressWarnings("rawtypes")
public class ProcedureDefinition {
	private String schemaName;
	private String name;
	private RowMapper rowMapper;
	private int outputType = 0;
	private int[] types;
	private String mapperName[];
	private String compiledSql;
	private boolean hasoutput = true;
	
	
	public ProcedureDefinition(String schemaName, String name,
			RowMapper rowMapper,Object... types) {
		this.schemaName = schemaName;
		this.name = name;
		this.rowMapper = rowMapper;
		initMappers(types);
	}


	public ProcedureDefinition(String schemaName, String name, boolean hasoutput
			,Object... types) {
		this.schemaName = schemaName;
		this.name = name;
		this.hasoutput = hasoutput;
		initMappers(types);
	}


	public ProcedureDefinition(String schemaName, String name, int outputType
			,Object... types) {
		this.schemaName = schemaName;
		this.name = name;
		this.outputType = outputType;
		initMappers(types);
	}
	
	public void initMappers(Object... deftypes) {
		this.types = new int[deftypes.length];
		this.mapperName = new String[deftypes.length];
		
		for(int i=0;i<deftypes.length;i++){
			this.types[i]=((Integer)deftypes[i]).intValue();
		}
		init();
	}
	
	public String getMapperName(int i) {
		if (mapperName != null && mapperName.length > i)
			return mapperName[i];

		return null;
	}
	
	public void init(){
		StringBuilder buf = new StringBuilder();
		buf.append("{CALL ");
		
		if(schemaName!=null)
			buf.append(schemaName +".");
		
//		buf.append(name + "(?,?");
		buf.append(name + "(?");
//		if(hasoutput)
//			buf.append(",?");
		if(types!=null){
			for(int i=0;i<types.length-1;i++){
				buf.append(",?");
			}
		}
		buf.append(")}");
		compiledSql = buf.toString();
		
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RowMapper getRowMapper() {
		return rowMapper;
	}

	public void setRowMapper(RowMapper rowMapper) {
		this.rowMapper = rowMapper;
	}

	public int getOutputType() {
		return outputType;
	}

	public void setOutputType(int outputType) {
		this.outputType = outputType;
	}

	public int[] getTypes() {
		return types;
	}

	public void setTypes(int[] types) {
		this.types = types;
	}

	public String getCompiledSQL() {
		return compiledSql;
	}

	public void setCompiledSQL(String compiledSql) {
		compiledSql = compiledSql;
	}

	public boolean isHasoutput() {
		return hasoutput;
	}

	public void setHasoutput(boolean hasoutput) {
		this.hasoutput = hasoutput;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	
	
	
}
