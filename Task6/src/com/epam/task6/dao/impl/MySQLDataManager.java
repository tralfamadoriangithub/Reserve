package com.epam.task6.dao.impl;

import java.util.ArrayList;
import java.util.Date;
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
		return connectionPool.getConnection();
	}

	private void releaseConnection( Connection connection ) throws SQLException {
		connectionPool.releaseConnection( connection );
		connection.close();
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
			while(rs.next()){
				Address address = new Address();
				address.setAddressId( rs.getInt( "address_id" ) );
				address.setStreet( rs.getString( "street" ) );
				address.setHouseNumber( rs.getInt( "house" ) );
				address.setBlockNumber( rs.getInt( "block" ) );
				address.setFlatNumber( rs.getInt( "flat" ) );
				address.setUserId( rs.getInt( "user_id" ) );
				addresses.add( address );
			}
			preparedStatement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			e.printStackTrace();
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
							"INSERT INTO claim (problem, address_id, user_id, status_id) VALUES (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS );
			preparedStatement.setString( 1, claim.getProblemDescription() );
			preparedStatement.setInt( 2, claim.getAddressId() );
			preparedStatement.setInt( 3, claim.getUserId() );
			preparedStatement.setInt( 4, 0 );
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if ( resultSet.next() ) {
				claim.setClaimId( resultSet.getInt( 1 ) );
				//newUserId = resultSet.getInt( 1 );
			}
			preparedStatement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			System.out.println( "addNewUser PrparedStatement exception " + e );
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Claim> getClaim( int... claimId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Claim> getUsersClaim( int... userId ) throws DaoException {
		connection = getConnection();
		List<Claim> claims = new ArrayList<Claim>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement( "SELECT * FROM claim WHERE user_id = ?" );
			preparedStatement.setInt( 1, userId[0] );
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Claim claim = new Claim();
				claim.setClaimId( rs.getInt( "claim_id" ) );
				claim.setProblemDescription( rs.getString( "problem" ) );
				claim.setAddressId( rs.getInt( "address_id" ) );
				claim.setUserId( rs.getInt( "user_id" ) );
				claim.setClaimStatusId( rs.getInt( "claim_status_id" ) );
				claims.add( claim );
			}
			preparedStatement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			e.printStackTrace();
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
			System.out.println( "addNewUser PrparedStatement exception " + e );
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
	public boolean updateAssignation( Assignation assignation ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAssignation( int assignationId ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Assignation> getAssignation( int... assignationId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addProfession( Profession profession ) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateProfession( Profession profession ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProfession( int professionId ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Profession> getProfession( int... professionId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addClaimStatus( ClaimStatus claimStatus ) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateClaimStatus( ClaimStatus claimStatus ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteClaimStatus( ClaimStatus claimStatus ) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ClaimStatus> getClaimStatus( int... claimId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> getAllAddresses() throws DaoException {
		
		connection = getConnection();
		List<Address> addresses = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM address");
			Address address;
			while(rs.next()){
				address = new Address();
				address.setAddressId( rs.getInt( "address_id" ) );
				address.setStreet( rs.getString( "street" ) );
				address.setHouseNumber( rs.getInt( "house" ) );
				address.setBlockNumber( rs.getInt( "block" ) );
				address.setFlatNumber( rs.getInt( "flat" ) );
				address.setUserId( rs.getInt( "user_id" ) );
				addresses.add( address );
			}
			statement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return addresses;
	}

	@Override
	public List<Claim> getAllClaims() throws DaoException {
		
		connection = getConnection();
		List<Claim> claims = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM claim");
			Claim claim;
			while(rs.next()){
				claim = new Claim();
				claim.setClaimId( rs.getInt( "claim_id" ) );
				claim.setProblemDescription( rs.getString( "problem" ) );
				claim.setAddressId( rs.getInt( "address_id" ) );
				claim.setUserId( rs.getInt( "user_id" ) );
				claim.setClaimStatusId( rs.getInt( "claim_status_id" ) );
				claims.add( claim );
			}
			statement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return claims;
	}

	@Override
	public List<User> getAllUsers() throws DaoException {
		List<User> users = new ArrayList<>();
		
		return users;
	}

	@Override
	public List<Worker> getAllWorkers() throws DaoException {
		
		connection = getConnection();
		List<Worker> workers = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM worker");
			Worker worker;
			while(rs.next()){
				worker = new Worker();
				worker.setWorkerId( rs.getInt( "worker_id" ) );
				worker.setName( rs.getString( "name" ) );
				worker.setSurname( rs.getString( "surname" ) );
				worker.setProfessionId( rs.getInt( "profession_id" ) );
				worker.setQualification( rs.getInt( "qualification" ) );
				worker.setAssignationId( rs.getInt( "assignation_id" ) );
				workers.add( worker );
			}
			statement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return workers;
	}

	@Override
	public List<Assignation> getAllAssignations() throws DaoException {
		
		connection = getConnection();
		List<Assignation> assignations = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM assignation");
			Assignation assignation;
			while(rs.next()){
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
			e.printStackTrace();
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
