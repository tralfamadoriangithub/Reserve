package com.epam.task6.dao;

import java.util.ResourceBundle;

import com.epam.task6.dao.impl.MySQLAccessDao;
import com.epam.task6.dao.impl.MySQLDataDao;

public class DaoFactory implements IDao {

	private static final DaoFactory instance;

	static {
		instance = new DaoFactory();
	}

	public synchronized static DaoFactory getInstance() {
		return instance;
	}

	@Override
	public IDataDao getDataDao( DataType dataType ) throws DaoException {

		IDataDao dataManager = null;

		if ( dataType != null ) {
			switch ( dataType ) {
			case MYSQL:
				dataManager = MySQLDataDao.getInstance();
				break;
			default:
				break;
			}
		}else{
			throw new DaoException("Exception in \"getDataDao\"");
		}
		return dataManager;
	}

	@Override
	public IDataDao getDataDao() throws DaoException {
		IDataDao dataManager;
		ResourceBundle bundle = ResourceBundle.getBundle( "project_properties" );
		DataType dataType = DataType.valueOf( bundle.getString( "data_type" )
				.toUpperCase() );
		
		dataManager = getDataDao( dataType );
		
		return dataManager;
	}

	@Override
	public IAccessDao getAccessDao( DataType dataType ) throws DaoException {

		IAccessDao accessManager = null;
		if ( null != dataType ) {
		switch ( dataType ) {
		case MYSQL:
			accessManager = MySQLAccessDao.getInstance();
			break;
		default:
			break;
		}
		}else{
			throw new DaoException("Exception in \"getAccessDao\"");
		}
		return accessManager;
	}

	@Override
	public IAccessDao getAccessDao() throws DaoException {
		IAccessDao accessManager;
		ResourceBundle bundle = ResourceBundle.getBundle( "project_properties" );
		DataType dataType = DataType.valueOf( bundle.getString( "data_type" )
				.toUpperCase() );
		
		accessManager = getAccessDao( dataType );
		
		return accessManager;
	}
}
