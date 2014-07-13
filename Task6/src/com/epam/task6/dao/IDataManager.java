package com.epam.task6.dao;

import java.sql.ResultSet;

public interface IDataManager {
	
	public ResultSet query( String query );

	public void update( String query );

	public void delete( String query );

	public void insert( String query );
}
