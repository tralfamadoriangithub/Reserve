package com.epam.task6.dao.impl.connectionpool;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

import com.epam.task6.dao.DaoException;
import com.epam.task6.propertylink.ProjectBundle;
import com.epam.task6.propertylink.ProjectProperties;
/**
 * Класс пула соединений.
 * 
 * Класс, отвечающий за создание пула соединений и управление им.
 * Экземпляр класса предоставляется методом <code>getInstance()</code>.
 * @author dmitry
 *
 */
public class ConnectionPool {

	private static ConnectionPool connectionPool;
	private ArrayBlockingQueue<Connection> freeConnections;
	private ArrayBlockingQueue<Connection> buisyCoonnections;

	private ConnectionPool() {
		int poolSize = getPoolSize();
		freeConnections = new ArrayBlockingQueue<>( poolSize );
		buisyCoonnections = new ArrayBlockingQueue<>( poolSize );
	}

	public static synchronized ConnectionPool getInstance() {
		if ( null == connectionPool ) {
			connectionPool = new ConnectionPool();
			connectionPool.initializeConnectionPool();
		}
		return connectionPool;
	}

	private void initializeConnectionPool() {

		ResourceBundle bundle = ResourceBundle.getBundle( ProjectBundle.PROJECT_PROPERTIES );
		String DRIVER = bundle.getString( ProjectProperties.DRIVER );
		String CONNECTION = bundle.getString( ProjectProperties.DATABASE_NAME );
		String USER = bundle.getString( ProjectProperties.DATABASE_USER );
		String PASSWORD = bundle.getString( ProjectProperties.DATABASE_PASSWORD );
		try {
			Class.forName( DRIVER );
			while ( freeConnections.remainingCapacity() > 0 ) {
				try {

					Connection conn = DriverManager.getConnection( CONNECTION,
							USER, PASSWORD );
					freeConnections.add( conn );

				} catch ( SQLException e ) {
					new DaoException(
							"Exception in \"initializeConnectionPool\"", e );
				}
			}

		} catch ( ClassNotFoundException e ) {
			new DaoException( "Exception in \"initializeConnectionPool\"", e );
		}
	}
	/**
	 * Метод для получения значения количества соединений из свойства <code>connections_limit</code>
	 * файла <code>project_properties</code>. Хардкод. Заменить более вменяемым.
	 * @return количество соединений в пуле.
	 */

	private int getPoolSize() {

		ResourceBundle bundle = ResourceBundle.getBundle( ProjectBundle.PROJECT_PROPERTIES );
		int poolSize = Integer
				.valueOf( bundle.getString( ProjectProperties.CONNECTIONS_LIMIT ) );

		return poolSize;
	}

	/**
	 * 
	 * Метод, возвращающий соединение из коллекции свободных соединений клиенту. 
	 * Ссылка на  соединение помещается также в коллекцию занятых соединений.
	 * 
	 * @return соединение с источником данных
	 * @throws DaoException выбрасывается при возникновении ощибки
	 */
	public Connection getConnection() throws DaoException {
		Connection connection = null;
		try {
			connection = freeConnections.take();
			buisyCoonnections.add( connection );
		} catch ( InterruptedException e ) {
			throw new DaoException( "Exception in \"getConnection\"", e );
		}
		return connection;
	}

	/**
	 * Метод, принимающий соединение от клиента и помещающие его в коллекцию свободных соединений.
	 * Предварительно производится проверка есть ли принятое соединение в коллекции занятых соединений пула.
	 * 
	 * @param conn соединение, принятое от клиента
	 * @throws DaoException выбрасывается, если переданное соединение не содержится в коллекции 
	 * занятых соединений пула
	 */
	public void releaseConnection( Connection conn ) throws DaoException {
		if ( buisyCoonnections.contains( conn ) ) {
			freeConnections.add( conn );
			buisyCoonnections.remove( conn );
		} else {
			throw new DaoException( "Exception in \"releaseConnection\"" );
		}
	}
	
	/**
	 * Метод, закрывающий все соединения пулаю
	 * @throws DaoException 
	 */

	private void closeConnections() throws DaoException {
		freeConnections.forEach( conn -> {
			try {
				conn.close();
			} catch ( SQLException e ) {
				new DaoException( "Exception in \"closeConnections\"", e );
			}
		} );

		buisyCoonnections.forEach( conn -> {
			try {
				conn.close();
			} catch ( SQLException e ) {
				new DaoException( "Exception in \"closeConnections\"", e );
			}
		} );
	}

	@Override
	protected void finalize() throws Throwable {
		closeConnections();
		super.finalize();
	}
}
