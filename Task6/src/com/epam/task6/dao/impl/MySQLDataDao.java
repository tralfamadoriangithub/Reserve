package com.epam.task6.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.epam.task6.dao.impl.connectionpool.ConnectionPool;
import com.epam.task6.dao.DBField;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.IDataDao;
import com.epam.task6.entity.Address;
import com.epam.task6.entity.Assignation;
import com.epam.task6.entity.Claim;
import com.epam.task6.entity.ClaimStatus;
import com.epam.task6.entity.Profession;
import com.epam.task6.entity.User;
import com.epam.task6.entity.Worker;
import com.epam.task6.tableentity.AssignationTableEntity;
import com.epam.task6.tableentity.ClaimTableEntity;
import com.epam.task6.tableentity.WorkerTableEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDataDao implements IDataDao {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private Connection connection;
	private static MySQLDataDao instance;

	private MySQLDataDao() {
	}

	public static synchronized MySQLDataDao getInstance() {
		if ( null == instance ) {
			instance = new MySQLDataDao();
		}
		return instance;
	}

	private Connection getConnection() throws DaoException {
		Connection conn = connectionPool.getConnection();
		return conn;
	}

	private void releaseConnection( Connection connection ) throws DaoException {
		connectionPool.releaseConnection( connection );
	}

	@Override
	public synchronized void addUser( User user ) throws DaoException {
		connection = getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection
					.prepareStatement(
							"INSERT INTO user (login, password, name, surname, status) VALUES (?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS );
			preparedStatement.setString( 1, user.getLogin() );
			preparedStatement.setString( 2, user.getPassword() );
			preparedStatement.setString( 3, user.getName() );
			preparedStatement.setString( 4, user.getSurname() );
			preparedStatement.setInt( 5, user.getStatus() );
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if ( resultSet.next() ) {
				user.setUserId( resultSet.getInt( 1 ) );
			}
			preparedStatement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"addUser\"", e );
		}
	}

	@Override
	public List<Address> getUsersAddress( int... userId ) throws DaoException {
		connection = getConnection();
		List<Address> addresses = new ArrayList<Address>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement( "SELECT * FROM address WHERE user_id = ?" );
			preparedStatement.setInt( 1, userId[0] );
			ResultSet rs = preparedStatement.executeQuery();
			
			Address address;
			
			while ( rs.next() ) {
				address = new Address();
				address.setAddressId( rs.getInt( DBField.ADDRESS_ID ) );
				address.setStreet( rs.getString( DBField.STREET ) );
				address.setHouseNumber( rs.getInt( DBField.HOUSE ) );
				address.setBlockNumber( rs.getInt( DBField.BLOCK ) );
				address.setFlatNumber( rs.getInt( DBField.FLAT ) );
				address.setPhone( rs.getString( DBField.PHONE ) );
				address.setUserId( rs.getInt( DBField.USER_ID ) );
				addresses.add( address );
			}
			preparedStatement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getUsersAddress\"", e );
		}
		return addresses;
	}

	@Override
	public synchronized void addClaim( Claim claim ) throws DaoException {
		connection = getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection
					.prepareStatement(
							"INSERT INTO claim (problem_description, address_id, user_id, claim_status_id) VALUES (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS );
			preparedStatement.setString( 1, claim.getProblemDescription() );
			preparedStatement.setInt( 2, claim.getAddressId() );
			preparedStatement.setInt( 3, claim.getUserId() );
			preparedStatement.setInt( 4, 0 );
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if ( resultSet.next() ) {
				claim.setClaimId( resultSet.getInt( 1 ) );
			}
			preparedStatement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"addClaim\"", e );
		}
	}

	@Override
	public List<ClaimTableEntity> getUsersClaim( User... users )
			throws DaoException {
		connection = getConnection();
		List<ClaimTableEntity> claims = new ArrayList<ClaimTableEntity>();

		try {
			for ( User user : users ) {
				PreparedStatement preparedStatement = connection
						.prepareStatement( "SELECT c.*, a.*, s.* FROM claim c JOIN address a ON c.address_id = a.address_id JOIN claim_status s ON c.claim_status_id = s.claim_status_id WHERE a.user_id = ?" );
				preparedStatement.setInt( 1, user.getUserId() );
				ResultSet rs = preparedStatement.executeQuery();
				
				ClaimTableEntity claim;
				Address address;
				
				while ( rs.next() ) {
					claim = new ClaimTableEntity();
					address = new Address();

					address.setAddressId( rs.getInt( DBField.ADDRESS_ID ) );
					address.setStreet( rs.getString( DBField.STREET ) );
					address.setHouseNumber( rs.getInt( DBField.HOUSE ) );
					address.setBlockNumber( rs.getInt( DBField.BLOCK ) );
					address.setFlatNumber( rs.getInt( DBField.FLAT ) );
					address.setPhone( rs.getString( DBField.PHONE ) );
					address.setUserId( rs.getInt( DBField.USER_ID ) );

					claim.setAddress( address );
					claim.setClaimId( rs.getInt( DBField.CLAIM_ID ) );
					claim.setProblemDescription( rs
							.getString( DBField.PROBLEM_DESCRIPTION ) );
					claim.setClaimStatus( rs
							.getString( DBField.CLAIM_STATUS_VALUE ) );

					claims.add( claim );
				}
				preparedStatement.close();
			}
			releaseConnection( connection );
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getUserClaims\"", e );
		}
		return claims;
	}

	@Override
	public List<Address> getAllAddresses() throws DaoException {

		connection = getConnection();
		List<Address> addresses = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery( "SELECT * FROM address" );
			Address address;
			while ( rs.next() ) {
				address = new Address();
				address.setAddressId( rs.getInt( DBField.ADDRESS_ID ) );
				address.setStreet( rs.getString( DBField.STREET ) );
				address.setHouseNumber( rs.getInt( DBField.HOUSE ) );
				address.setBlockNumber( rs.getInt( DBField.BLOCK ) );
				address.setFlatNumber( rs.getInt( DBField.FLAT ) );
				address.setPhone( rs.getString( DBField.PHONE ) );
				address.setUserId( rs.getInt( DBField.USER_ID ) );
				addresses.add( address );
			}
			statement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getAllAddresses\"", e );
		}
		return addresses;
	}

	@Override
	public List<ClaimTableEntity> getAllClaims() throws DaoException {

		connection = getConnection();
		List<ClaimTableEntity> claims = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery( "SELECT c.*, a.*, s.* FROM claim c JOIN address a ON c.address_id = a.address_id JOIN claim_status s ON c.claim_status_id = s.claim_status_id" );
			ClaimTableEntity claim;
			Address address;
			
			while ( rs.next() ) {
				claim = new ClaimTableEntity();
				address = new Address();

				address.setAddressId( rs.getInt( DBField.ADDRESS_ID ) );
				address.setStreet( rs.getString( DBField.STREET ) );
				address.setHouseNumber( rs.getInt( DBField.HOUSE ) );
				address.setBlockNumber( rs.getInt( DBField.BLOCK ) );
				address.setFlatNumber( rs.getInt( DBField.FLAT ) );
				address.setPhone( rs.getString( DBField.PHONE ) );
				address.setUserId( rs.getInt( DBField.USER_ID ) );

				claim.setAddress( address );
				claim.setClaimId( rs.getInt( DBField.CLAIM_ID ) );
				claim.setProblemDescription( rs
						.getString( DBField.PROBLEM_DESCRIPTION ) );
				claim.setClaimStatus( rs
						.getString( DBField.CLAIM_STATUS_VALUE ) );
				claims.add( claim );
			}
			statement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getAllClaims\"", e );
		}
		return claims;
	}

	@Override
	public List<User> getAllUsers() throws DaoException {
		List<User> users = new ArrayList<>();

		return users;
	}

	@Override
	public List<WorkerTableEntity> getAllWorkers() throws DaoException {

		connection = getConnection();
		List<WorkerTableEntity> workers = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery( "SELECT w.*, p.* FROM worker w JOIN profession p ON w.profession_id = p.profession_id" );

			WorkerTableEntity worker;
			Profession profession;

			while ( rs.next() ) {
				worker = new WorkerTableEntity();
				profession = new Profession();

				profession.setProfessionId( rs.getInt( DBField.PROFESSION_ID ) );
				profession.setProfessionName( rs.getString( DBField.PROFESSION ) );

				worker.setProfession( profession );
				worker.setWorkerId( rs.getInt( DBField.WORKER_ID ) );
				worker.setName( rs.getString( DBField.WORKER_NAME ) );
				worker.setSurname( rs.getString( DBField.WORKER_SURNAME ) );
				worker.setQualification( rs.getInt( DBField.QUALIFICATION ) );
				
				workers.add( worker );
			}
			statement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getAllWorkers\"", e );
		}
		return workers;
	}

	@Override
	public List<AssignationTableEntity> getAllAssignations()
			throws DaoException {

		connection = getConnection();
		List<AssignationTableEntity> assignations = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery( "SELECT a.*, c.*, addr.*, c_s.* FROM assignation a JOIN claim c ON a.claim_id = c.claim_id JOIN address addr ON c.address_id = addr.address_id JOIN claim_status c_s ON c.claim_status_id = c_s.claim_status_id" );
		
			AssignationTableEntity assignation;
			ClaimTableEntity claim;
			Address address;
			
			while ( rs.next() ) {
				assignation = new AssignationTableEntity();
				claim = new ClaimTableEntity();
				address = new Address();

				address.setAddressId( rs.getInt( DBField.ADDRESS_ID ) );
				address.setStreet( rs.getString( DBField.STREET ) );
				address.setHouseNumber( rs.getInt( DBField.HOUSE ) );
				address.setBlockNumber( rs.getInt( DBField.BLOCK ) );
				address.setFlatNumber( rs.getInt( DBField.FLAT ) );
				address.setPhone( rs.getString( DBField.PHONE ) );
				address.setUserId( rs.getInt( DBField.USER_ID ) );

				claim.setAddress( address );
				claim.setClaimId( rs.getInt( DBField.CLAIM_ID ) );
				claim.setClaimStatus( rs.getString( DBField.CLAIM_STATUS_VALUE ) );
				claim.setProblemDescription( rs
						.getString( DBField.PROBLEM_DESCRIPTION ) );

				assignation.setClaim( claim );
				assignation.setAssignationId( rs.getInt( DBField.ASSIGNATION_ID ) );
				assignation.setBeginWork( rs.getTimestamp( DBField.BEGIN_WORK ) );
				assignation.setEndWork( rs.getTimestamp( DBField.END_WORK ) );

				assignations.add( assignation );
			}
			statement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getAllAssignations\"", e );
		}
		return assignations;
	}

	@Override
	public void registerNewAssignation( Assignation assignation,
			int... workersId ) throws DaoException {
		connection = getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection
					.prepareStatement(
							"INSERT INTO assignation (begin_work, end_work, claim_id) VALUES (?,?,?)",
							Statement.RETURN_GENERATED_KEYS );
			preparedStatement.setTimestamp( 1, assignation.getBeginWork() );
			preparedStatement.setTimestamp( 2, assignation.getEndWork() );
			preparedStatement.setInt( 3, assignation.getClaimId() );
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if ( resultSet.next() ) {
				assignation.setAssignationId( resultSet.getInt( 1 ) );
			}
			preparedStatement.close();

			preparedStatement = connection
					.prepareStatement(
							"INSERT INTO worker_assignation (assignation_id, worker_id ) VALUES (?,?)",
							Statement.RETURN_GENERATED_KEYS );
			int assignationId = assignation.getAssignationId();
			for ( int workerId : workersId ) {
				preparedStatement.setInt( 1, assignationId );
				preparedStatement.setInt( 2, workerId );
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			preparedStatement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"addClaim\"", e );
		}
	}

	@Override
	public HashMap<String, String> query( String query ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void addAddress( Address address ) throws DaoException {

	}

	@Override
	public boolean updateAddress( Address address ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAddress( int addressId ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Address> getAddress( int... userId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateClaim( Claim claim ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteClaim( int claimId ) throws DaoException {

		return false;
	}

	@Override
	public List<Claim> getClaim( int... claimId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser( User user ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser( int userId ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getUser( int... userId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void addWorker( Worker worker ) throws DaoException {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean updateWorker( Worker worker ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteWorker( int workerId ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Worker> getWorker( int... workerId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void addAssignation( Assignation assignation )
			throws DaoException {
		connection = getConnection();

	}

	@Override
	public boolean updateAssignation( Assignation assignation )
			throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAssignation( int assignationId ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Assignation> getAssignation( int... assignationId )
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void addProfession( Profession profession )
			throws DaoException {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean updateProfession( Profession profession )
			throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProfession( int professionId ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Profession> getProfession( int... professionId )
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void addClaimStatus( ClaimStatus claimStatus )
			throws DaoException {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean updateClaimStatus( ClaimStatus claimStatus )
			throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteClaimStatus( ClaimStatus claimStatus )
			throws DaoException {

		return false;
	}

	@Override
	public List<ClaimStatus> getClaimStatus( int... claimId )
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profession> getAllProfessions() throws DaoException {
		List<Profession> professions = new ArrayList<>();

		return professions;
	}

	@Override
	public List<ClaimStatus> getAllClaimStatuses() throws DaoException {
		List<ClaimStatus> claimStatuses = new ArrayList<>();

		return claimStatuses;
	}
}
