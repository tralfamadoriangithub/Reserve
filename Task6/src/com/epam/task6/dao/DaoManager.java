package com.epam.task6.dao;

public abstract class DaoManager {

	private DataType dataType;

	public abstract IDataManager getDataManager();

	public abstract IAccessManager getAccessManager();

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType( DataType dataType ) {
		this.dataType = dataType;
	}

	private class AccessManagerFactory {

	}
	
	private class DataManagerFactory{
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataType == null) ? 0 : dataType.hashCode());
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		DaoManager other = (DaoManager) obj;
		if ( dataType != other.dataType )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [dataType=" + dataType + "]";
	}

}
