package com.epam.task6.dao.impl;

import com.epam.task6.dao.DaoManager;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.IDataManager;

public class OracleDaoManager extends DaoManager {

	private static OracleDaoManager instance;

	private OracleDaoManager() {

	}

	public static OracleDaoManager getInstance() {
		if ( instance == null ) {
			instance = new OracleDaoManager();
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
