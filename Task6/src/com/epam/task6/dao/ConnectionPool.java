package com.epam.task6.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

	private static ConnectionPool connectionPool;
	private ArrayBlockingQueue<Connection> freeConnections;
	private ArrayBlockingQueue<Connection> buisyCoonnections;
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String CONNECTION = "jdbc:mysql://localhost/mysql";
	private final String USER = "root";
	private final String PASSWORD = "";

	private ConnectionPool( int size ) {

		int poolSize = size > 0 ? size : 10;
		freeConnections = new ArrayBlockingQueue<>( poolSize );
		buisyCoonnections = new ArrayBlockingQueue<>( poolSize );
		initializeConnectionPool();
	}

	public static ConnectionPool getInstance() {
		if ( null == connectionPool ) {
			connectionPool = new ConnectionPool( 5 );
		}
		return connectionPool;
	}

	private void initializeConnectionPool() {

		try {

			Class.forName( DRIVER );

			Properties connectionProperties = new Properties();
			connectionProperties.put( "driver", DRIVER );
			connectionProperties.put( "user", USER );
			connectionProperties.put( "password", PASSWORD );
			for ( int i = 0; i < freeConnections.size(); i++ ) {
				try {
					freeConnections.add( DriverManager.getConnection(
							CONNECTION, connectionProperties ) );
				} catch ( SQLException e ) {
					e.printStackTrace();
				}
			}
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}

	public synchronized Connection getConnection() {
		Connection connection = null;
		try {
			connection = freeConnections.take();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
		buisyCoonnections.add( connection );
		return connection;
	}

	public synchronized void releaseConnection( Connection conn ) {
		if ( buisyCoonnections.contains( conn ) ) {
			buisyCoonnections.remove( conn );
			freeConnections.add( conn );
		} else {

		}
	}

	
	
	private void closeConnections(){
		freeConnections.forEach( conn -> {
			try {
				conn.close();
			} catch ( SQLException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} );

		buisyCoonnections.forEach( conn -> {
			try {
				conn.close();
			} catch ( SQLException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} );
	}
	
	@Override
	protected void finalize() throws Throwable {
		closeConnections();
		super.finalize();
	}
}
