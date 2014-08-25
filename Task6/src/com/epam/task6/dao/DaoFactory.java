package com.epam.task6.dao;
import java.util.ResourceBundle;

import com.epam.task6.dao.impl.MySQLAccessDao;
import com.epam.task6.dao.impl.MySQLDataDao;
import com.epam.task6.propertylink.ProjectBundle;
import com.epam.task6.propertylink.ProjectProperties;
/**
 * Фабрика экземпляров доступа к данным.
 * 
 * Класс, представляющий фабрику для доступа к экземплярам IAccessDao и IDataDao,
 * соответствующих текущему типу данных. Экземпляр класса предоставляется методом
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
	 * Возвращает объект IDataDao предназначенный для взаимодействия с источником данных.
	 * 
	 * @param dataType тип данных источника.
	 * @return экземпляр IDataDao
	 * @exception DaoException при возникновении ошибки.
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
	 * Возвращает объект IDataDao предназначенный для взаимодействия с источником данных.
	 * 
	 * Тип данных <strong>обязан</strong> быть указан в параметре <code>data_type</code> файла 
	 * <code>project_properties.properties</code>.
	 * <strong>Данная реализация никуда не годится.</strong>
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
	 * Возвращает объект IAccessDao предназначенный для логинации пользователя системы.
	 * 
	 * @param dataType тип данных источника.
	 * @return экземпляр IAccessDao
	 * @exception DaoException при возникновении ошибки.
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
	 * Возвращает объект IAccessDao предназначенный для логинации пользователя системы.
	 * 
	 * Тип данных <strong>обязан</strong> быть указан в параметре <code>data_type</code> файла 
	 * <code>project_properties.properties</code>.
	 * <strong>Данная реализация никуда не годится.</strong>
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
