package com.epam.task6.dao;

public interface IDao {
	
	public IDataDao getDataDao( DataType dataType ) throws DaoException;

	public IDataDao getDataDao() throws DaoException;

	public IAccessDao getAccessDao( DataType dataType ) throws DaoException;

	public IAccessDao getAccessDao() throws DaoException;
}
