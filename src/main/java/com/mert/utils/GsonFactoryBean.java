package com.mert.utils;

import org.springframework.beans.factory.FactoryBean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactoryBean implements FactoryBean<Gson> {

	private boolean prettyPrinting = false;
	private String dateFormat = null;

	
	public Gson getObject() throws Exception {
		GsonBuilder builder = new GsonBuilder();
		if (dateFormat != null)
			builder.setDateFormat(dateFormat);

		if (prettyPrinting)
			builder.setPrettyPrinting();

		return builder.create();
	}

	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Gson.class;
	}

	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPrettyPrinting() {
		return prettyPrinting;
	}

	public void setPrettyPrinting(boolean prettyPrinting) {
		this.prettyPrinting = prettyPrinting;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	


}
