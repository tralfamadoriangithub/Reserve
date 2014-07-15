package com.epam.task6.dao.impl.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

	private static ConnectionPool connectionPool;
	private ArrayBlockingQueue<Connection> freeConnections;
	private ArrayBlockingQueue<Connection> buisyCoonnections;
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String CONNECTION = "jdbc:mysql://localhost/zkh";
	private final String USER = "root";
	private final String PASSWORD = "928851";

	private ConnectionPool( int size ) {

		int poolSize = size > 0 ? size : 10;
		freeConnections = new ArrayBlockingQueue<>( poolSize );
		buisyCoonnections = new ArrayBlockingQueue<>( poolSize );
		initializeConnectionPool();

	}

	public static ConnectionPool getInstance() {
		if ( null == connectionPool ) {
			connectionPool = new ConnectionPool( 10 );
		}
		return connectionPool;
	}

	private void initializeConnectionPool() {
		System.out.println( "Init connection pool" );
		for ( int i = 0; i < freeConnections.size(); i++ ) {
			try {
				Class.forName( DRIVER );

				try {
					Connection conn = DriverManager.getConnection( CONNECTION,
							USER, PASSWORD );
					System.out.println( conn );
					freeConnections.add( conn );

				} catch ( SQLException e ) {
					e.printStackTrace();
				}

			} catch ( ClassNotFoundException e ) {
				e.printStackTrace();
			}
		}
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

	public synchronized void releaseConnection( Connection conn ) {
		if ( buisyCoonnections.contains( conn ) ) {
			buisyCoonnections.remove( conn );
			freeConnections.add( conn );
		} else {

		}
	}

	public Connection hardConnection() {
		System.out.println( "Hard connection" );
		Connection connection = null;
		try {
			Class.forName( DRIVER );

			try {
				connection = DriverManager.getConnection( CONNECTION, USER,
						PASSWORD );
			} catch ( SQLException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch ( ClassNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( "Return connection" );
		System.out.println( connection );
		return connection;
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
