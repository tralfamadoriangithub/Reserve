package com.epam.task6.dao;

public interface IDao {
	public DataManager getDataManager( DataType dataType );
	public DataManager getDataManager();
	public IAccessManager getAccessManager(DataType dataType );
	public IAccessManager getAccessManager();
}
