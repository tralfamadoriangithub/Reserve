package com.epam.task6.dao;

import java.util.HashMap;

public abstract class DataManager {

	private DataType dataType;

	public abstract HashMap<String, String> query( String query );

	public abstract void update( String query );

	public abstract void delete( String query );

	public abstract void insert( String query );

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType( DataType dataType ) {
		this.dataType = dataType;
	}
	

}
