package com.epam.task6.dao;

public interface IDao {
	public IDataManager getDataManager( DataType dataType );
	public IDataManager getDataManager();
	public IAccessManager getAccessManager(DataType dataType );
	public IAccessManager getAccessManager();
}
