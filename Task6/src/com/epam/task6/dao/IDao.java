package com.epam.task6.dao;

public interface IDao {
	public IDataDao getDataDao( DataType dataType );
	public IDataDao getDataDao();
	public IAccessDao getAccessDao(DataType dataType );
	public IAccessDao getAccessDao();
}
