package com.epam.task6.dao;

import com.epam.task6.entity.User;

public interface IAccessDao {
	public User signIn( String login, String password ) throws DaoException;
	public void signOut();
	public void register( String login, String password );
	public User register (User newUser );
}
