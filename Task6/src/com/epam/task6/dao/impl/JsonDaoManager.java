package com.epam.task6.dao.impl;

import com.epam.task6.dao.DaoManager;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.IDataManager;

public class JsonDaoManager extends DaoManager {

	private static JsonDaoManager instance;

	private JsonDaoManager() {

	}

	public static JsonDaoManager getInstance() {
		if ( instance == null ) {
			instance = new JsonDaoManager();
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
