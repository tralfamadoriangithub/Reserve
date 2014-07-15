package com.epam.task6.dao.impl;

import java.util.HashMap;

import com.epam.task6.dao.impl.connectionpool.ConnectionPool;
import com.epam.task6.dao.DataEntity;
import com.epam.task6.dao.DataManager;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDataManager extends DataManager{

	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private Connection connection;
	private Statement statement;
	
	@Override
	public HashMap<String, String> query( String query ) {
		return null;
	}

	@Override
	public void update( String query ) {
	}

	@Override
	public void delete( String query ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert( String query ) {
		
	}
	
	private void linkToDataSource() {
		System.out.println("Get connection");
		connection = connectionPool.hardConnection();
		try {
			statement = connection.createStatement();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		System.out.println("Connected");
	}

	private void unlinkFromDataSource() {
		connectionPool.releaseConnection( connection );
		try {
			statement.close();
			connection.close();
		} catch ( SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
