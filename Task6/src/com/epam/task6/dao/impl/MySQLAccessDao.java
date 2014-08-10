package com.epam.task6.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.IAccessDao;
import com.epam.task6.dao.impl.connectionpool.ConnectionPool;
import com.epam.task6.entity.User;
import com.mysql.jdbc.PreparedStatement;

public class MySQLAccessDao implements IAccessDao {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private Connection connection;
	private static MySQLAccessDao instance;
	
	private MySQLAccessDao() {
		
	}
	
	public static synchronized MySQLAccessDao getInstance(){
		if(instance == null){
			instance = new MySQLAccessDao();
		}
		return instance;
	}

	@Override
	public User signIn( final String login, final String password ) throws DaoException {
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
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"signIn\" method" , e );  
		}finally{
			connectionPool.releaseConnection( connection );
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

	@Override
	public User register( User newUser ) {
		// TODO Auto-generated method stub
		return null;
	}

}
