package com.epam.task6.dao;

import java.sql.ResultSet;
import java.util.HashMap;

public interface IDataManager {
	
	public HashMap<String, String> query( String query );

	public void update( String query );

	public void delete( String query );

	public void insert( String query );
}
