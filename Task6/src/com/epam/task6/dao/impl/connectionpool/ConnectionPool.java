package com.epam.task6.dao.impl.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

	private static ConnectionPool connectionPool;
	private int defaultPoolSize;
	private ArrayBlockingQueue<Connection> freeConnections;
	private ArrayBlockingQueue<Connection> buisyCoonnections;
	private ArrayBlockingQueue<Connection> testQueue;
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String CONNECTION = "jdbc:mysql://localhost/zkh";
	private final String USER = "root";
	private final String PASSWORD = "928851";

	private ConnectionPool() {

		int poolSize = getPoolSize();
		if ( poolSize < 1 ) {
			poolSize = defaultPoolSize;
		}
		freeConnections = new ArrayBlockingQueue<>( poolSize );
		buisyCoonnections = new ArrayBlockingQueue<>( poolSize );
		testQueue = new ArrayBlockingQueue<>( poolSize );
		initializeConnectionPool();

	}

	public static ConnectionPool getInstance() {
		if ( null == connectionPool ) {
			connectionPool = new ConnectionPool();
		}
		return connectionPool;
	}

	private void initializeConnectionPool() {
		System.out.println( "Init connection pool" );
		try {
			Class.forName( DRIVER );

			while ( freeConnections.remainingCapacity() > 0 ) {
				try {
					System.out.println( "Trying create pool" );
					Connection conn = DriverManager.getConnection( CONNECTION,
							USER, PASSWORD );
					System.out.println( conn );
					freeConnections.add( conn );

				} catch ( SQLException e ) {
					e.printStackTrace();
				}
			}

		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}

	private int getPoolSize() {

		int poolSize = -1;
		ResourceBundle bundle = ResourceBundle.getBundle( "project_properties" );
		try {
			poolSize = Integer
					.valueOf( bundle.getString( "connections_limit" ) );
		} catch ( MissingResourceException e ) {

		}
		return poolSize;
	}

	public Connection getConnection() {
		Connection connection = null;
		System.out.println( "getConnection" );
		try {
			connection = freeConnections.take();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
		buisyCoonnections.add( connection );
		System.out.println( "returnConnection" );
		return connection;
	}

	public void releaseConnection( Connection conn ) {
		if ( buisyCoonnections.contains( conn ) ) {
			buisyCoonnections.remove( conn );
			freeConnections.add( conn );
		} else {

		}
	}

	private void closeConnections() {
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
