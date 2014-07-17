package com.epam.task6.dao.impl;

import com.epam.task6.dao.DaoManager;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.IDataManager;

public class MySQLDaoManager extends DaoManager{

	private IDataManager dataManager;
	private IAccessManager accessManager;
	
	private static MySQLDaoManager instance;
	
	private MySQLDaoManager() {
		
	}
	
	public static MySQLDaoManager getInstance(){
		if( instance == null ){
			instance = new MySQLDaoManager();
		}
		return instance;
	}
	
	@Override
	public IDataManager getDataManager() {
		
		return null;
	}

	@Override
	public IAccessManager getAccessManager() {
		accessManager = MySQLAccessManager.getInstance();
		return accessManager;
	}

}
