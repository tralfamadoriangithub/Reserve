package com.epam.task6.dao;

import java.util.ResourceBundle;

import com.epam.task6.dao.impl.MySQLAccessManager;
import com.epam.task6.dao.impl.MySQLDataManager;

public class DaoFactory implements IDao {

	private static final DaoFactory instance;

	static {
		instance = new DaoFactory();
	}

	public static DaoFactory getInstance() {
		return instance;
	}

	@Override
	public IDataManager getDataManager( DataType dataType ) {

		IDataManager dataManager = null;

		switch ( dataType ) {
		case MYSQL:
			dataManager = MySQLDataManager.getInstance();
			break;
		default:
			break;
		}
		return dataManager;
	}

	@Override
	public IDataManager getDataManager() {
		IDataManager dataManager;
		ResourceBundle bundle = ResourceBundle.getBundle( "project_properties" );
		DataType dataType = DataType.valueOf( bundle.getString( "data_type" )
				.toUpperCase() );
		if ( null != dataType ) {
			dataManager = getDataManager( dataType );
		} else {
			dataManager = null;
		}
		return dataManager;
	}

	@Override
	public IAccessManager getAccessManager( DataType dataType ) {

		IAccessManager accessManager = null;
		switch ( dataType ) {
		case MYSQL:
			accessManager = MySQLAccessManager.getInstance();
			break;
		default:
			break;
		}
		return accessManager;
	}

	@Override
	public IAccessManager getAccessManager() {
		IAccessManager accessManager;
		ResourceBundle bundle = ResourceBundle.getBundle( "project_properties" );
		DataType dataType = DataType.valueOf( bundle.getString( "data_type" )
				.toUpperCase() );
		if ( null != dataType ) {
			accessManager = getAccessManager( dataType );
		} else {
			accessManager = null;
		}
		return accessManager;
	}
}
