package com.epam.task6.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.impl.connectionpool.ConnectionPool;
import com.epam.task6.entity.User;
import com.mysql.jdbc.PreparedStatement;

public class MySQLAccessManager implements IAccessManager {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private Connection connection;
	private static MySQLAccessManager instance;
	
	private MySQLAccessManager() {
		
	}
	
	public static MySQLAccessManager getInstance(){
		if(instance == null){
			instance = new MySQLAccessManager();
		}
		return instance;
	}

	@Override
	public User signIn( final String login, final String password ) {
		User user = null;
		connection = connectionPool.getConnection();
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement( "SELECT * FROM user WHERE login = ?" );
			preparedStatement.setString( 1, login );

			ResultSet resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				if ( resultSet.getString( "password" ).equals( password ) ) {
					user = new User();
					user.setLogin( login );
					user.setPassword( password );
					user.setName( resultSet.getString( "name" ) );
					user.setSurname( resultSet.getString( "surname" ) );
					user.setUserId( resultSet.getInt( "user_id" ) );
					user.setStatus( resultSet.getInt( "status" ) );
				}
			}
			resultSet.close();
			connectionPool.releaseConnection( connection );
			connection.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void signOut() {
		// TODO Auto-generated method stub

	}

	@Override
	public void register( String user, String password ) {
		// TODO Auto-generated method stub

	}

}
