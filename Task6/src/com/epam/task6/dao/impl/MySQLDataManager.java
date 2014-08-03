package com.epam.task6.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.epam.task6.dao.impl.connectionpool.ConnectionPool;
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
	public HashMap<String, String> query( String query ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addAddress( Address address ) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateAddress( Address address ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAddress( int addressId ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Address> getAddress( int... userId ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> getUsersAddress( int... userId ) {
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
	public int addClaim( Claim claim ) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateClaim( Claim claim ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteClaim( int claimId ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Claim> getClaim( int... claimId ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Claim> getUsersClaim( int... userId ) {
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
				claim.setClaimStatusId( rs.getInt( "status_id" ) );
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
	public int addUser( User user ) {
		connection = getConnection();
		PreparedStatement preparedStatement;
		int newUserId = -1;
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
				//newUserId = resultSet.getInt( 1 );
			}
			preparedStatement.close();
			releaseConnection( connection );
		} catch ( SQLException e ) {
			System.out.println( "addNewUser PrparedStatement exception " + e );
		}
		return newUserId;
	}

	@Override
	public boolean updateUser( User user ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser( int userId ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getUser( int... userId ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addWorker( Worker worker ) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateWorker( Worker worker ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteWorker( int workerId ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Worker> getWorker( int... workerId ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addAssignation( Assignation assignation ) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateAssignation( Assignation assignation ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAssignation( int assignationId ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Assignation> getAssignation( int... assignationId ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addProfession( Profession profession ) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateProfession( Profession profession ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProfession( int professionId ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Profession> getProfession( int... professionId ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addClaimStatus( ClaimStatus claimStatus ) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateClaimStatus( ClaimStatus claimStatus ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteClaimStatus( ClaimStatus claimStatus ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ClaimStatus> getClaimStatus( int... claimId ) {
		// TODO Auto-generated method stub
		return null;
	}
}
