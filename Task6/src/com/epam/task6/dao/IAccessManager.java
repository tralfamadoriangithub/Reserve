package com.epam.task6.dao;

import com.epam.task6.entity.User;

public interface IAccessManager {
	public User signIn( String login, String password );
	public void signOut();
	public void register( String login, String password );
}
