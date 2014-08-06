package com.epam.task6.dao.impl;

import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.entity.User;

public class XmlAccessManager implements IAccessManager {

	private static XmlAccessManager instance;

	private XmlAccessManager() {
		// TODO Auto-generated constructor stub
	}

	public static XmlAccessManager getInstance() {
		if ( instance == null ) {
			instance = new XmlAccessManager();
		}
		return instance;
	}

	@Override
	public User signIn( String user, String password ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
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
