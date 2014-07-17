package com.epam.task6.dao;

import java.util.ResourceBundle;

import com.epam.task6.dao.impl.JsonAccessManager;
import com.epam.task6.dao.impl.JsonDaoManager;
import com.epam.task6.dao.impl.JsonDataManager;
import com.epam.task6.dao.impl.MySQLAccessManager;
import com.epam.task6.dao.impl.MySQLDaoManager;
import com.epam.task6.dao.impl.MySQLDataManager;
import com.epam.task6.dao.impl.OracleAccessManager;
import com.epam.task6.dao.impl.OracleDaoManager;
import com.epam.task6.dao.impl.OracleDataManager;
import com.epam.task6.dao.impl.XmlAccessManager;
import com.epam.task6.dao.impl.XmlDaoManager;
import com.epam.task6.dao.impl.XmlDataManager;

public class DaoFactory implements IDao {

	private static final DaoFactory instance;

	static {
		instance = new DaoFactory();
	}

	public static DaoFactory getInstance() {
		return instance;
	}

	@Override
	public DataManager getDataManager( DataType dataType ) {

		DataManager dataManager;

		switch ( dataType ) {
		case JSON:
			dataManager = JsonDataManager.getInstance();
			break;

		case XML:
			dataManager = XmlDataManager.getInstance();
			break;

		case MYSQL:
			dataManager = MySQLDataManager.getInstance();
			break;

		case ORACLE:
			dataManager = OracleDataManager.getInstance();
			break;

		default:
			dataManager = null;
			break;
		}
		if ( dataManager != null ) {
			dataManager.setDataType( dataType );
		}
		return dataManager;
	}

	@Override
	public DataManager getDataManager() {
		DataManager dataManager;
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
		case JSON:
			accessManager = JsonAccessManager.getInstance();
			break;
		case MYSQL:
			accessManager = MySQLAccessManager.getInstance();
			break;
		case XML:
			accessManager = XmlAccessManager.getInstance();
			break;
		case ORACLE:
			accessManager = OracleAccessManager.getInstance();
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
