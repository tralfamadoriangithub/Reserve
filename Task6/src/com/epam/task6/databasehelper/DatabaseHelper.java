package com.epam.task6.databasehelper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.epam.task6.dao.DaoException;

public final class DatabaseHelper {

	private static DatabaseHelper instance;

	private String Driver = "com.mysql.jdbc.Driver";
	private String User = "root";
	private String Password = "928851";

	private DatabaseHelper() {

	}

	public static DatabaseHelper getInstance() {
		if ( instance == null ) {
			instance = new DatabaseHelper();
		}
		return instance;
	}

	public void createDatabase() throws DaoException {
		try {
			Class.forName( Driver );
			try {
				Properties connectionProperties = new Properties();
				connectionProperties.put( "driver", Driver );
				connectionProperties.put( "user", User );
				connectionProperties.put( "password", Password );
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/mysql", connectionProperties );
				Statement statement = connection.createStatement();
				DatabaseMetaData dataBaseMetaData = connection.getMetaData();
				ResultSet resultSet = dataBaseMetaData.getCatalogs();

				boolean databaseExists = false;
				while ( resultSet.next() ) {
					if ( resultSet.getString( 1 ).equals( "zkh" ) ) {
						System.out.println( "Database zkh already exists" );
						statement.close();
						connection.close();
						databaseExists = true;
					}
				}

				if ( !databaseExists ) {
					statement.executeUpdate( "CREATE DATABASE zkh  CHARACTER SET utf8 COLLATE utf8_unicode_ci" );
					resultSet.close();
					statement.close();
					connection.close();
					System.out.println("Database created");
				}
			} catch ( SQLException e ) {
				throw new DaoException( "Create DB exception", e );
			}
		} catch ( ClassNotFoundException e ) {
			throw new DaoException( "Database driver not found", e );
		}
		fillDatabase();
	}

	public void deleteDatabase() throws DaoException {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName( Driver );
			try {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/zkh", User, Password );
				try {
					statement = connection.createStatement();
					statement.executeUpdate( "DROP DATABASE zkh" );
					statement.close();
					connection.close();
					System.out.println("Database deleted");
				} catch ( SQLException e ) {
					throw new DaoException( "Can not create statement ", e );
				}
			} catch ( SQLException e ) {
				throw new DaoException( "Can not create connection ", e );
			}
		} catch ( ClassNotFoundException e ) {
			throw new DaoException( "Can not download driver ", e );
		}
	}

	public void fillDatabase() throws DaoException {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName( Driver );
			try {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/zkh", User, Password );
				//connection.setAutoCommit( false );
				try {
					statement = connection.createStatement();
					createTables( statement );
					fillTables( statement );
					statement.close();
					connection.close();
				} catch ( SQLException e ) {
					throw new DaoException( "Can not create statement ", e );
				}
			} catch ( SQLException e ) {
				throw new DaoException( "Can not create connection ", e );
			}
		} catch ( ClassNotFoundException e ) {
			throw new DaoException( "Can not download driver ", e );
		}

	}

	private void createTables( Statement statement ) throws SQLException {

		statement
				.addBatch( "CREATE TABLE user (user_id INT PRIMARY KEY AUTO_INCREMENT, login VARCHAR(25) NOT NULL, password VARCHAR(25) NOT NULL, name CHAR(25) NOT NULL, surname CHAR(25) NOT NULL, status INT)" );
		statement
				.addBatch( "CREATE TABLE address (address_id INT PRIMARY KEY AUTO_INCREMENT, street VARCHAR(25) NOT NULL, house INT, block INT DEFAULT 0, flat INT, phone INT, user_id INT)" );
		statement
				.addBatch( "CREATE TABLE claim_status (claim_status_id INT PRIMARY KEY, value VARCHAR(25) NOT NULL)" );
		statement
				.addBatch( "CREATE TABLE claim (claim_id INT PRIMARY KEY AUTO_INCREMENT, problem VARCHAR(250) NOT NULL, address_id INT, user_id INT, claim_status_id INT DEFAULT 0)" );
		statement
				.addBatch( "CREATE TABLE worker (worker_id INT PRIMARY KEY AUTO_INCREMENT, name CHAR(25) NOT NULL, surname CHAR(25) NOT NULL, profession_id INT, qualification INT, assignation_id INT DEFAULT 0)" );
		statement
				.addBatch( "CREATE TABLE profession (profession_id INT PRIMARY KEY AUTO_INCREMENT, profession CHAR(25) NOT NULL)" );
		statement
				.addBatch( "CREATE TABLE assignation (assignation_id INT PRIMARY KEY AUTO_INCREMENT, begin_work DATETIME, end_work DATETIME, claim_id INT DEFAULT 0)" );
		statement.executeBatch();
	}

	private void fillTables( Statement statement ) throws SQLException {

		statement
				.addBatch( "INSERT INTO user VALUES (1, 'USER 1', 'ONE', 'name1', 'surname1', 1)" );
		statement
				.addBatch( "INSERT INTO user VALUES (2, 'USER 2', 'TWO', 'name2', 'surname2', 2)" );
		statement
				.addBatch( "INSERT INTO user VALUES (3, 'USER 3', 'THREE', 'name3', 'surname3', 3)" );

		statement
				.addBatch( "INSERT INTO address VALUES (1, 'Чайлытко', '1', 0, '5', 44455545, 1)" );
		statement
				.addBatch( "INSERT INTO address VALUES (2, 'Уманская', 6, 2, 1, 847364536, 1)" );
		statement
				.addBatch( "INSERT INTO address VALUES (3, 'Сапеги', 4, 0, 5, 435345345, 2)" );

		statement.addBatch( "INSERT INTO claim_status VALUES (0, 'Excepted')" );
		statement.addBatch( "INSERT INTO claim_status VALUES (1, 'Processed')" );
		statement.addBatch( "INSERT INTO claim_status VALUES (2, 'Done')" );
		statement.addBatch( "INSERT INTO claim_status VALUES (3, 'Cancelled')" );

		statement
				.addBatch( "INSERT INTO claim VALUES (1, 'протекает кран', 3, 2, 0)" );
		statement
				.addBatch( "INSERT INTO claim VALUES (2, 'рухнул потолок', 1, 1, 0)" );
		statement
				.addBatch( "INSERT INTO claim VALUES (3, 'засорился унитаз', 2, 1, 0)" );

		statement.addBatch( "INSERT INTO profession VALUES (1, 'сантехник')" );
		statement.addBatch( "INSERT INTO profession VALUES (2, 'штукатур')" );
		statement.addBatch( "INSERT INTO profession VALUES (3, 'электрик')" );
		statement.addBatch( "INSERT INTO profession VALUES (4, 'маляр')" );

		statement
				.addBatch( "INSERT INTO worker VALUES (1, 'Петр', 'Петров', 1, 5, 0)" );
		statement
				.addBatch( "INSERT INTO worker VALUES (2, 'Иван', 'Иванов', 2, 3, 0)" );
		statement
				.addBatch( "INSERT INTO worker VALUES (3, 'Федор', 'Федоров', 2, 4, 0)" );
		statement
				.addBatch( "INSERT INTO worker VALUES (4, 'Сидор', 'Сидоров', 3, 5, 0)" );
		statement
				.addBatch( "INSERT INTO worker VALUES (5, 'Барак', 'Обама', 1, 3, 0)" );
		statement
				.addBatch( "INSERT INTO worker VALUES (6, 'Билл', 'Гейтс', 4, 3, 0)" );
		int[] batch2 = statement.executeBatch();
		for ( int i : batch2 ) {
			System.out.println( "Batch 2 " + batch2[i] );
		}
	}

	public void createTables2() throws DaoException {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName( Driver );
			try {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/zkh", User, Password );
				connection.setAutoCommit( false );
				try {
					statement = connection.createStatement();

					statement
							.addBatch( "CREATE TABLE user (user_id INT PRIMARY KEY AUTO_INCREMENT, login VARCHAR(25) NOT NULL, password VARCHAR(25) NOT NULL, name CHAR(25) NOT NULL, surname CHAR(25) NOT NULL, status INT)" );
					statement
							.addBatch( "CREATE TABLE address (address_id INT PRIMARY KEY AUTO_INCREMENT, street VARCHAR(25) NOT NULL, house INT, block INT DEFAULT 0, flat INT, phone INT, user_id INT)" );
					statement
							.addBatch( "CREATE TABLE claim_status (claim_status_id INT PRIMARY KEY, value VARCHAR(25) NOT NULL)" );
					statement
							.addBatch( "CREATE TABLE claim (claim_id INT PRIMARY KEY AUTO_INCREMENT, problem VARCHAR(250) NOT NULL, address_id INT, user_id INT, claim_status_id INT DEFAULT 0)" );
					statement
							.addBatch( "CREATE TABLE worker (worker_id INT PRIMARY KEY AUTO_INCREMENT, name CHAR(25) NOT NULL, surname CHAR(25) NOT NULL, profession_id INT, qualification INT, assignation_id INT DEFAULT 0)" );
					statement
							.addBatch( "CREATE TABLE profession (profession_id INT PRIMARY KEY AUTO_INCREMENT, profession CHAR(25) NOT NULL)" );
					statement
							.addBatch( "CREATE TABLE assignation (assignation_id INT PRIMARY KEY AUTO_INCREMENT, begin_work DATETIME, end_work DATETIME, claim_id INT DEFAULT 0)" );

					statement.close();
					connection.setAutoCommit( true );
					connection.close();
				} catch ( SQLException e ) {
					throw new DaoException( "Can not create statement ", e );
				}
			} catch ( SQLException e ) {
				throw new DaoException( "Can not create connection ", e );
			}
		} catch ( ClassNotFoundException e ) {
			throw new DaoException( "Can not download driver ", e );
		}
	}

	public void fillTables2() throws DaoException {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName( Driver );
			try {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/zkh", User, Password );
				connection.setAutoCommit( false );
				try {
					statement = connection.createStatement();

					statement
							.addBatch( "INSERT INTO user VALUES (1, 'USER 1', 'ONE', 'name1', 'surname1', 1)" );
					statement
							.addBatch( "INSERT INTO user VALUES (2, 'USER 2', 'TWO', 'name2', 'surname2', 2)" );
					statement
							.addBatch( "INSERT INTO user VALUES (3, 'USER 3', 'THREE', 'name3', 'surname3', 3)" );

					statement
							.addBatch( "INSERT INTO address VALUES (1, 'Chaylytko', '1', 0, '5', 44455545, 1)" );
					statement
							.addBatch( "INSERT INTO address VALUES (2, 'Umanskaya', 6, 2, 1, 847364536, 1)" );
					statement
							.addBatch( "INSERT INTO address VALUES (3, 'Sapegi', 4, 0, 5, 435345345, 2)" );

					statement
							.addBatch( "INSERT INTO claim_status VALUES (0, 'Excepted')" );
					statement
							.addBatch( "INSERT INTO claim_status VALUES (1, 'Processed')" );
					statement
							.addBatch( "INSERT INTO claim_status VALUES (2, 'Done')" );
					statement
							.addBatch( "INSERT INTO claim_status VALUES (3, 'Cancelled')" );

					statement
							.addBatch( "INSERT INTO claim VALUES (1, 'potek kran', 3, 2, 0)" );
					statement
							.addBatch( "INSERT INTO claim VALUES (2, 'рухнул потолок', 1, 1, 0)" );
					statement
							.addBatch( "INSERT INTO claim VALUES (3, 'zasorilsya unitaz', 2, 1, 0)" );

					statement
							.addBatch( "INSERT INTO profession VALUES (1, 'santehnik')" );
					statement
							.addBatch( "INSERT INTO profession VALUES (2, 'shtukatur')" );
					statement
							.addBatch( "INSERT INTO profession VALUES (3, 'electric')" );
					statement
							.addBatch( "INSERT INTO profession VALUES (4, 'malyar')" );

					statement
							.addBatch( "INSERT INTO worker VALUES (1, 'Petr', 'Petrov', 1, 5, 0)" );
					statement
							.addBatch( "INSERT INTO worker VALUES (2, 'Ivan', 'Ivanov', 2, 3, 0)" );
					statement
							.addBatch( "INSERT INTO worker VALUES (3, 'Fedor', 'Fedorov', 2, 4, 0)" );
					statement
							.addBatch( "INSERT INTO worker VALUES (4, 'Sidor', 'Sidorov', 3, 5, 0)" );
					statement
							.addBatch( "INSERT INTO worker VALUES (5, 'Abram', 'Stoltz', 1, 3, 0)" );
					statement
							.addBatch( "INSERT INTO worker VALUES (6, 'Ivan', 'Starov', 4, 3, 0)" );

					statement.close();
					connection.setAutoCommit( true );
					connection.close();
				} catch ( SQLException e ) {
					throw new DaoException( "Can not create statement ", e );
				}
			} catch ( SQLException e ) {
				throw new DaoException( "Can not create connection ", e );
			}
		} catch ( ClassNotFoundException e ) {
			throw new DaoException( "Can not download driver ", e );
		}

	}
}
