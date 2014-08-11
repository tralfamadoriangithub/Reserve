package com.epam.task6.dao.impl.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

import com.epam.task6.dao.DaoException;

public class ConnectionPool {

	private static ConnectionPool connectionPool;
	private ArrayBlockingQueue<Connection> freeConnections;
	private ArrayBlockingQueue<Connection> buisyCoonnections;

	private ConnectionPool() {
		int poolSize = getPoolSize();
		freeConnections = new ArrayBlockingQueue<>( poolSize );
		buisyCoonnections = new ArrayBlockingQueue<>( poolSize );
	}

	public static synchronized ConnectionPool getInstance() {
		if ( null == connectionPool ) {
			connectionPool = new ConnectionPool();
			connectionPool.initializeConnectionPool();
		}
		return connectionPool;
	}

	private void initializeConnectionPool() {

		ResourceBundle bundle = ResourceBundle.getBundle( "project_properties" );
		String DRIVER = bundle.getString( "driver" );
		String CONNECTION = bundle.getString( "database_name" );
		String USER = bundle.getString( "user" );
		String PASSWORD = bundle.getString( "password" );
		try {
			Class.forName( DRIVER );
			while ( freeConnections.remainingCapacity() > 0 ) {
				try {

					Connection conn = DriverManager.getConnection( CONNECTION,
							USER, PASSWORD );
					freeConnections.add( conn );

				} catch ( SQLException e ) {
					new DaoException(
							"Exception in \"initializeConnectionPool\"", e );
				}
			}

		} catch ( ClassNotFoundException e ) {
			new DaoException( "Exception in \"initializeConnectionPool\"", e );
		}
	}

	private int getPoolSize() {

		ResourceBundle bundle = ResourceBundle.getBundle( "project_properties" );
		int poolSize = Integer
				.valueOf( bundle.getString( "connections_limit" ) );

		return poolSize;
	}

	public Connection getConnection() throws DaoException {
		Connection connection = null;
		try {
			connection = freeConnections.take();
			buisyCoonnections.add( connection );
		} catch ( InterruptedException e ) {
			throw new DaoException( "Exception in \"getConnection\"", e );
		}
		return connection;
	}

	public void releaseConnection( Connection conn ) throws DaoException {
		if ( buisyCoonnections.contains( conn ) ) {
			freeConnections.add( conn );
			buisyCoonnections.remove( conn );
		} else {
			throw new DaoException( "Exception in \"releaseConnection\"" );
		}
	}

	private void closeConnections() throws DaoException {
		freeConnections.forEach( conn -> {
			try {
				conn.close();
			} catch ( SQLException e ) {
				new DaoException( "Exception in \"closeConnections\"", e );
			}
		} );

		buisyCoonnections.forEach( conn -> {
			try {
				conn.close();
			} catch ( SQLException e ) {
				new DaoException( "Exception in \"closeConnections\"", e );
			}
		} );
	}

	@Override
	protected void finalize() throws Throwable {
		closeConnections();
		super.finalize();
	}
}
