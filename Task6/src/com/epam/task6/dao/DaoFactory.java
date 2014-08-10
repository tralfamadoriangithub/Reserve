package com.epam.task6.dao;

import java.util.ResourceBundle;

import com.epam.task6.dao.impl.MySQLAccessDao;
import com.epam.task6.dao.impl.MySQLDataDao;

public class DaoFactory implements IDao {

	private static final DaoFactory instance;

	static {
		instance = new DaoFactory();
	}

	public static DaoFactory getInstance() {
		return instance;
	}

	@Override
	public IDataDao getDataDao( DataType dataType ) {

		IDataDao dataManager = null;

		switch ( dataType ) {
		case MYSQL:
			dataManager = MySQLDataDao.getInstance();
			break;
		default:
			break;
		}
		return dataManager;
	}

	@Override
	public IDataDao getDataDao() {
		IDataDao dataManager;
		ResourceBundle bundle = ResourceBundle.getBundle( "project_properties" );
		DataType dataType = DataType.valueOf( bundle.getString( "data_type" )
				.toUpperCase() );
		if ( null != dataType ) {
			dataManager = getDataDao( dataType );
		} else {
			dataManager = null;
		}
		return dataManager;
	}

	@Override
	public IAccessDao getAccessDao( DataType dataType ) {

		IAccessDao accessManager = null;
		switch ( dataType ) {
		case MYSQL:
			accessManager = MySQLAccessDao.getInstance();
			break;
		default:
			break;
		}
		return accessManager;
	}

	@Override
	public IAccessDao getAccessDao() {
		IAccessDao accessManager;
		ResourceBundle bundle = ResourceBundle.getBundle( "project_properties" );
		DataType dataType = DataType.valueOf( bundle.getString( "data_type" )
				.toUpperCase() );
		if ( null != dataType ) {
			accessManager = getAccessDao( dataType );
		} else {
			accessManager = null;
		}
		return accessManager;
	}
}
