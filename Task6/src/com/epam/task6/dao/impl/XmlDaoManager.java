package com.epam.task6.dao.impl;

import com.epam.task6.dao.DaoManager;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.IDataManager;

public class XmlDaoManager extends DaoManager {

	private static XmlDaoManager instance;

	private XmlDaoManager() {

	}

	public static XmlDaoManager getInstance() {
		if ( instance == null ) {
			instance = new XmlDaoManager();
		}
		return instance;
	}

	@Override
	public IDataManager getDataManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAccessManager getAccessManager() {
		// TODO Auto-generated method stub
		return null;
	}

}
