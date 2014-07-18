package com.epam.task6.dao.impl;

import java.util.HashMap;

import com.epam.task6.dao.impl.connectionpool.ConnectionPool;
import com.epam.task6.dao.DataManager;
import com.epam.task6.dao.IDataManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDataManager implements IDataManager{

	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private Connection connection;
	private Statement statement;
	private static MySQLDataManager instance;
	
	private MySQLDataManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static MySQLDataManager getInstance(){
		if( null == instance ){
			instance = new MySQLDataManager();
		}
		return instance;
	}
	

	
//	private void linkToDataSource() {
//		System.out.println("Get connection");
//		connection = connectionPool.hardConnection();
//		try {
//			statement = connection.createStatement();
//		} catch ( SQLException e ) {
//			e.printStackTrace();
//		}
//		System.out.println("Connected");
//	}

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

	@Override
	public HashMap<String, String> query( String query ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAddress( String street, int house, int block, int flat,
			int userId ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAddress( int addressId ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAddress( int userId ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addClaim( int addressId, int userId, String problemDescription ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getClaim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClaimStatus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteClaim() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createSquad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSquad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAssignment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWorker() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWorker() {
		// TODO Auto-generated method stub
		
	}

}
