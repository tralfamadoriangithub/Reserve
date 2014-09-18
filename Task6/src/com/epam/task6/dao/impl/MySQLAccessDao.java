package com.epam.task6.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.task6.dao.DBField;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.IAccessDao;
import com.epam.task6.dao.DataNotFoundException;
import com.epam.task6.dao.impl.connectionpool.ConnectionPool;
import com.epam.task6.entity.User;
import com.mysql.jdbc.PreparedStatement;
/**
 * Класс, реализующий интерфейс {@link com.epam.task6.dao.IAccessDao} для логинации 
 * с использованием БД MySQL.
 * @author dmitry
 *
 */
public class MySQLAccessDao implements IAccessDao {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();
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
	public User signIn( final String login, final String password ) throws DaoException, DataNotFoundException {
		User user = null;
		Connection connection = connectionPool.getConnection();
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection
					.prepareStatement( "SELECT * FROM user WHERE login = ?" );
			preparedStatement.setString( 1, login );

			ResultSet resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				if ( resultSet.getString( DBField.PASSWORD ).equals( password ) ) {
					user = new User();
					user.setLogin( login );
					user.setPassword( password );
					user.setName( resultSet.getString( DBField.NAME ) );
					user.setSurname( resultSet.getString( DBField.SURNAME ) );
					user.setUserId( resultSet.getInt( DBField.USER_ID ) );
					user.setStatus( resultSet.getInt( DBField.USER_STATUS ) );
					resultSet.close();
				}else{
					throw new DataNotFoundException( "Wrong login or password" );
				}
			}else{
				throw new DataNotFoundException( "No such user" );
			}
			
			
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"signIn\" method" , e );  
		}finally{
			connectionPool.releaseConnection( connection );
		}
		
		return user;
	}
}
