package com.epam.task6.dao;
import java.util.ResourceBundle;

import com.epam.task6.dao.impl.MySQLAccessDao;
import com.epam.task6.dao.impl.MySQLDataDao;
import com.epam.task6.propertylink.ProjectBundle;
import com.epam.task6.propertylink.ProjectProperties;
/**
 * ������� ����������� ������� � ������.
 * 
 * �����, �������������� ������� ��� ������� � ����������� IAccessDao � IDataDao,
 * ��������������� �������� ���� ������. ��������� ������ ��������������� �������
 * <code>getInstance()</code>
 * 
 * @author dmitry
 *
 */
public class DaoFactory implements IDao {

	private static final DaoFactory instance;

	static {
		instance = new DaoFactory();
	}

	public synchronized static DaoFactory getInstance() {
		return instance;
	}

	/**
	 * ���������� ������ IDataDao ��������������� ��� �������������� � ���������� ������.
	 * 
	 * @param dataType ��� ������ ���������.
	 * @return ��������� IDataDao
	 * @exception DaoException ��� ������������� ������.
	 */
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

	/**
	 * ���������� ������ IDataDao ��������������� ��� �������������� � ���������� ������.
	 * 
	 * ��� ������ <strong>������</strong> ���� ������ � ��������� <code>data_type</code> ����� 
	 * <code>project_properties.properties</code>.
	 * <strong>������ ���������� ������ �� �������.</strong>
	 */
	
	@Override
	public IDataDao getDataDao() throws DaoException {
		IDataDao dataManager;
		ResourceBundle bundle = ResourceBundle.getBundle( ProjectBundle.PROJECT_PROPERTIES );
		DataType dataType = DataType.valueOf( bundle.getString( ProjectProperties.DATA_TYPE )
				.toUpperCase() );
		
		dataManager = getDataDao( dataType );
		
		return dataManager;
	}

	/**
	 * ���������� ������ IAccessDao ��������������� ��� ��������� ������������ �������.
	 * 
	 * @param dataType ��� ������ ���������.
	 * @return ��������� IAccessDao
	 * @exception DaoException ��� ������������� ������.
	 */
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

	/**
	 * ���������� ������ IAccessDao ��������������� ��� ��������� ������������ �������.
	 * 
	 * ��� ������ <strong>������</strong> ���� ������ � ��������� <code>data_type</code> ����� 
	 * <code>project_properties.properties</code>.
	 * <strong>������ ���������� ������ �� �������.</strong>
	 */
	@Override
	public IAccessDao getAccessDao() throws DaoException {
		IAccessDao accessManager;
		ResourceBundle bundle = ResourceBundle.getBundle( ProjectBundle.PROJECT_PROPERTIES );
		DataType dataType = DataType.valueOf( bundle.getString( ProjectProperties.DATA_TYPE )
				.toUpperCase() );
		
		accessManager = getAccessDao( dataType );
		
		return accessManager;
	}
}
