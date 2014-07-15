package com.epam.task6.dao;

import java.util.ResourceBundle;

import com.epam.task6.dao.impl.JsonAccessManager;
import com.epam.task6.dao.impl.JsonDataManager;
import com.epam.task6.dao.impl.MySQLAccessManager;
import com.epam.task6.dao.impl.MySQLDataManager;
import com.epam.task6.dao.impl.OracleAccessManager;
import com.epam.task6.dao.impl.OracleDataManager;
import com.epam.task6.dao.impl.XmlAccessManager;
import com.epam.task6.dao.impl.XmlDataManager;

public class DaoFactory implements IDao{
	
	private static final DaoFactory instance;
	
	static {
		instance = new DaoFactory();
	}
	
	public static DaoFactory getInstance(){
		return instance;
	}

	@Override
	public DataManager getDataManager( DataType dataType ) {
		
		DataManager dataManager;
		
		switch ( dataType ) {
		case JSON :
			dataManager = new JsonDataManager();
			break;
			
		case XML:
			dataManager = new XmlDataManager();
			break;
			
		case MYSQL:
			dataManager = new MySQLDataManager();
			break;
			
		case ORACLE:
			dataManager = new OracleDataManager();
			break;

		default:
			dataManager = null;
			break;
		}
		if(dataManager != null){
			dataManager.setDataType( dataType );
		}
		return dataManager;
	}

	@Override
	public DataManager getDataManager() {
		DataManager dataManager;
		ResourceBundle bundle = ResourceBundle.getBundle( "project_properties" );
		DataType dataType = DataType.valueOf( bundle.getString( "database" ).toUpperCase() );
		if( null != dataType ){
			dataManager = getDataManager( dataType );
		}else{
			dataManager = null;
		}
		return dataManager;
	}

	@Override
	public IAccessManager getAccessManager( DataManager dataManager ) {
		
		IAccessManager accessManager = null;
		switch ( dataManager.getDataType() ) {
		case JSON:
			accessManager = new JsonAccessManager();
			break;
		case MYSQL:
			accessManager = new MySQLAccessManager();
			break;
		case XML:
			accessManager = new XmlAccessManager();
			break;
		case ORACLE:
			accessManager = new OracleAccessManager();
			break;
		default:
			break;
		}
		return accessManager;
	}

	@Override
	public IAccessManager getAccessManager() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
