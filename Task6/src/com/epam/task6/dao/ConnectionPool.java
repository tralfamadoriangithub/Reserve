package com.epam.task6.dao;

public class ConnectionPool {
	
	private static ConnectionPool connectionPool;
	
	private ConnectionPool(){	
	}
	public static ConnectionPool getInstance(){
		if(null == connectionPool){
			connectionPool = new ConnectionPool();
		}
		return connectionPool;
	}
}
