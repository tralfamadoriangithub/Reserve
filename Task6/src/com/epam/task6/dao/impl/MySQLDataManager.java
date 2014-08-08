package com.epam.task6.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.epam.task6.dao.impl.connectionpool.ConnectionPool;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.entity.Address;
import com.epam.task6.entity.Assignation;
import com.epam.task6.entity.Claim;
import com.epam.task6.entity.ClaimStatus;
import com.epam.task6.entity.Profession;
import com.epam.task6.entity.User;
import com.epam.task6.entity.Worker;
import com.epam.task6.tableentity.ClaimTableEntity;
import com.epam.task6.tableentity.WorkerTableEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDataManager implements IDataManager {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private Connection connection;
	private static MySQLDataManager instance;

	private MySQLDataManager() {
	}

	public static MySQLDataManager getInstance() {
		if ( null == instance ) {
			instance = new MySQLDataManager();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = connectionPool.getConnection();
		return conn;
	}

	private void releaseConnection( Connection connection ) throws SQLException {
		connectionPool.releaseConnection( connection );
		//connection.close();
	}

	@Override
	public HashMap<String, String> query( String query ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addAddress( Address address ) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
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
	public List<Address> getUsersAddress( int... userId ) throws DaoException {
		connection = getConnection();
		List<Address> addresses = new ArrayList<Address>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement( "SELECT * FROM address WHERE user_id = ?" );
			preparedStatement.setInt( 1, userId[0] );
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				Address address = new Address();
				address.setAddressId( rs.getInt( "address_id" ) );
				address.setStreet( rs.getString( "street" ) );
				address.setHouseNumber( rs.getInt( "house" ) );
				address.setBlockNumber( rs.getInt( "block" ) );
				address.setFlatNumber( rs.getInt( "flat" ) );
				address.setPhone( rs.getString( "phone" ) );
				address.setUserId( rs.getInt( "user_id" ) );
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
	public int addClaim( Claim claim ) throws DaoException {
		connection = getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection
					.prepareStatement(
							"INSERT INTO claim (problem, address_id, user_id, claim_status_id) VALUES (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS );
			preparedStatement.setString( 1, claim.getProblemDescription() );
			preparedStatement.setInt( 2, claim.getAddressId() );
			preparedStatement.setInt( 3, claim.getUserId() );
			preparedStatement.setInt( 4, 0 );
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if ( resultSet.next() ) {
				claim.setClaimId( resultSet.getInt( 1 ) );
				// newUserId = resultSet.getInt( 1 );
			}
			preparedStatement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"addClaim\"", e );
		}
		return claim.getClaimId();
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
	public List<ClaimTableEntity> getUsersClaim( User... users )
			throws DaoException {
		connection = getConnection();
		List<ClaimTableEntity> claims = new ArrayList<ClaimTableEntity>();

		try {
			for ( User user : users ) {
				PreparedStatement preparedStatement = connection
						.prepareStatement( "SELECT c.*, a.*, s.value FROM claim c JOIN address a ON c.address_id = a.address_id JOIN claim_status s ON c.claim_status_id = s.claim_status_id WHERE a.user_id = ?" );
				preparedStatement.setInt( 1, user.getUserId() );
				ResultSet rs = preparedStatement.executeQuery();
				while ( rs.next() ) {
					ClaimTableEntity claim = new ClaimTableEntity();
					Address address = new Address();
					
					claim.setClaimId( rs.getInt( "claim_id" ) );
					claim.setProblemDescription( rs.getString( "problem" ) );
					claim.setClaimStatus( rs.getString( "value" ) );
					
					address.setAddressId( rs.getInt( "address_id" ) );
					address.setStreet( rs.getString( "street" ) );
					address.setHouseNumber( rs.getInt( "house" ) );
					address.setBlockNumber( rs.getInt( "block" ) );
					address.setFlatNumber( rs.getInt( "flat" ) );
					address.setPhone( rs.getString( "phone" ) );
					address.setUserId( rs.getInt( "user_id" ) );
					
					claim.setAddress( address );
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
	public int addUser( User user ) throws DaoException {
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
		return 0;
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
	public int addWorker( Worker worker ) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
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
	public int addAssignation( Assignation assignation ) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
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
	public int addProfession( Profession profession ) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
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
	public int addClaimStatus( ClaimStatus claimStatus ) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ClaimStatus> getClaimStatus( int... claimId )
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
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
				address.setAddressId( rs.getInt( "address_id" ) );
				address.setStreet( rs.getString( "street" ) );
				address.setHouseNumber( rs.getInt( "house" ) );
				address.setBlockNumber( rs.getInt( "block" ) );
				address.setFlatNumber( rs.getInt( "flat" ) );
				address.setPhone( rs.getString( "phone" ) );
				address.setUserId( rs.getInt( "user_id" ) );
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
			ResultSet rs = statement.executeQuery( "SELECT c.*, a.*, s.value FROM claim c JOIN address a ON c.address_id = a.address_id JOIN claim_status s ON c.claim_status_id = s.claim_status_id" );
			ClaimTableEntity claim;
			while ( rs.next() ) {
				 claim = new ClaimTableEntity();
				Address address = new Address();
				
				claim.setClaimId( rs.getInt( "claim_id" ) );
				claim.setProblemDescription( rs.getString( "problem" ) );
				claim.setClaimStatus( rs.getString( "value" ) );
				
				address.setAddressId( rs.getInt( "address_id" ) );
				address.setStreet( rs.getString( "street" ) );
				address.setHouseNumber( rs.getInt( "house" ) );
				address.setBlockNumber( rs.getInt( "block" ) );
				address.setFlatNumber( rs.getInt( "flat" ) );
				address.setUserId( rs.getInt( "user_id" ) );
				
				claim.setAddress( address );
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
			ResultSet rs = statement.executeQuery( "SELECT w.*, p.* FROM worker w JOIN profession p ON w.profession_id = p.profession_id" );
			
			WorkerTableEntity worker;
			Profession profession;
			Assignation assignation;
			
			while ( rs.next() ) {
				worker = new WorkerTableEntity();
				profession = new Profession();
				
				profession.setProfessionId( rs.getInt( "profession_id" ) );
				profession.setProfessionName( rs.getString( "profession" ) );
				
				worker.setWorkerId( rs.getInt( "worker_id" ) );
				worker.setName( rs.getString( "name" ) );
				worker.setSurname( rs.getString( "surname" ) );
				worker.setQualification( rs.getInt( "qualification" ) );
				worker.setAssignationId( rs.getInt( "assignation_id" ) );
				worker.setProfession( profession );
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
	public List<Assignation> getAllAssignations() throws DaoException {

		connection = getConnection();
		List<Assignation> assignations = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery( "SELECT * FROM assignation" );
			Assignation assignation;
			while ( rs.next() ) {
				assignation = new Assignation();
				assignation.setAssignationId( rs.getInt( "assignation_id" ) );
				assignation.setClaimId( rs.getInt( "claim_id" ) );
				assignation.setBeginWork( rs.getDate( "begin_work" ) );
				assignation.setEndWork( rs.getDate( "end_work" ) );
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
