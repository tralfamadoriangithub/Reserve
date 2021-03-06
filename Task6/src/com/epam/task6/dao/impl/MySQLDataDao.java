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
import com.epam.task6.entity.ClaimStatusIdValue;
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
/**
 * �����, ����������� ��������� {@link com.epam.task6.dao.IDataDao} ��� �������������� � �� MySQL.
 * @author dmitry
 *
 */
public class MySQLDataDao implements IDataDao {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();
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
		
		Connection connection = getConnection();
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
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"addUser\"", e );
		}finally{
			releaseConnection( connection );
		}
	}

	@Override
	public List<Address> getUsersAddress( int... userId ) throws DaoException {
		
		Connection connection = getConnection();
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
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getUsersAddress\"", e );
		}finally{
			releaseConnection( connection );
		}
		return addresses;
	}

	@Override
	public synchronized void addClaim( Claim claim ) throws DaoException {
		
		Connection connection = getConnection();
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
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"addClaim\"", e );
		}finally{
			releaseConnection( connection );
		}
	}

	@Override
	public List<ClaimTableEntity> getUsersClaim( User... users )
			throws DaoException {
		
		Connection connection = getConnection();
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
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getUserClaims\"", e );
		}finally{
			releaseConnection( connection );
		}
		return claims;
	}

	@Override
	public List<Address> getAllAddresses() throws DaoException {

		Connection connection = getConnection();
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
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getAllAddresses\"", e );
		}finally{
			releaseConnection( connection );
		}
		return addresses;
	}

	@Override
	public List<ClaimTableEntity> getAllClaims() throws DaoException {

		Connection connection = getConnection();
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
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getAllClaims\"", e );
		}finally{
			releaseConnection( connection );
		}
		return claims;
	}

	@Override
	public List<WorkerTableEntity> getAllWorkers() throws DaoException {

		Connection connection = getConnection();
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
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getAllWorkers\"", e );
		}finally{
			releaseConnection( connection );
		}
		return workers;
	}

	@Override
	public List<AssignationTableEntity> getAllAssignations()
			throws DaoException {

		Connection connection = getConnection();
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

				int assignation_id = rs.getInt( DBField.ASSIGNATION_ID );
				assignation.setClaim( claim );
				assignation.setAssignationId( assignation_id );
				assignation.setBeginWork( rs.getTimestamp( DBField.BEGIN_WORK ) );
				assignation.setEndWork( rs.getTimestamp( DBField.END_WORK ) );

				assignations.add( assignation );
			}
			statement.close();
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"getAllAssignations\"", e );
		}finally{
			releaseConnection( connection );
		}
		return assignations;
	}

	@Override
	public void registerNewAssignation( Assignation assignation,
			int... workersId ) throws DaoException {
		
		Connection connection = getConnection();
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
			preparedStatement = connection.prepareStatement( "UPDATE claim SET claim_status_id = ? WHERE claim_id = ? ");
			preparedStatement.setInt( 1, ClaimStatusIdValue.PROCESSED );
			preparedStatement.setInt( 2, assignation.getClaimId() );
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"addClaim\"", e );
		}finally{
			releaseConnection( connection );
		}
	}
	
	@Override
	public void deleteAssignation( int assignationId , int claimId  ) throws DaoException {
		
		Connection connection = getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection
					.prepareStatement(
							"DELETE FROM assignation WHERE assignation_id = ?");
			preparedStatement.setInt( 1, assignationId );
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection
					.prepareStatement(
							"DELETE FROM worker_assignation WHERE assignation_id = ?");
			preparedStatement.setInt( 1, assignationId );
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement( "UPDATE claim SET claim_status_id = ? WHERE claim_id = ? ");
			preparedStatement.setInt( 1, ClaimStatusIdValue.EXCEPTED );
			preparedStatement.setInt( 2, claimId );
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"deleteAssignation\"", e );
		}finally{
			releaseConnection( connection );
		}
	}
	
	@Override
	public List<User> getAllUsers() throws DaoException {
		List<User> users = new ArrayList<>();

		return users;
	}

	@Override
	public synchronized void addAddress( Address address ) throws DaoException {
		
		Connection connection = getConnection();
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection
					.prepareStatement(
							"INSERT INTO address (street, house, block, flat, phone, user_id) VALUES (?,?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS );
			preparedStatement.setString( 1, address.getStreet() );
			preparedStatement.setInt( 2, address.getHouseNumber() );
			preparedStatement.setInt( 3, address.getBlockNumber() );
			preparedStatement.setInt( 4, address.getFlatNumber() );
			preparedStatement.setString( 5, address.getPhone() );
			preparedStatement.setInt( 6, address.getUserId() );
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if ( resultSet.next() ) {
				address.setAddressId( resultSet.getInt( 1 ) );
			}
			preparedStatement.close();
			
		}catch( SQLException e){
			throw new DaoException( "Exception in \"addAddress\"", e);
		}finally{
			releaseConnection( connection );
		}
	}
	
	@Override
	public synchronized void updateClaim( int claimId , String problemDescription  ) throws DaoException {
		
		Connection connection = getConnection();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement( "UPDATE claim SET problem_description = ? WHERE claim_id = ?" );
			preparedStatement.setString( 1, problemDescription );
			preparedStatement.setInt( 2, claimId );
			preparedStatement.executeUpdate();
			preparedStatement.close();		
		} catch ( SQLException e ) {
			throw new DaoException( "Exception in \"updateClaim\"", e);
		}finally{
			releaseConnection( connection );
		}
	}
	
	@Override
	public void deleteClaim( int claimId ) throws DaoException {
		
		Connection connection = getConnection();
		PreparedStatement preparedStatement;
		try{
			preparedStatement = connection.prepareStatement( "DELETE FROM claim WHERE claim_id = ?" );
			preparedStatement.setInt( 1, claimId );
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}catch(SQLException e){
			throw new DaoException( "Exception in \"deleteClaim\"", e );
		}finally{
			releaseConnection( connection );
		}
	}

	@Override
	public synchronized void updateAddress( Address address ) throws DaoException {
		
	}

	@Override
	public void deleteAddress( int addressId ) throws DaoException {
		;
	}

	@Override
	public List<Address> getAddress( int... userId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Claim> getClaim( int... claimId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser( User user ) throws DaoException {
		
	}

	@Override
	public void deleteUser( int userId ) throws DaoException {
		
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
	public void updateWorker( Worker worker ) throws DaoException {
		
	}

	@Override
	public void deleteWorker( int workerId ) throws DaoException {
		;
	}

	@Override
	public List<Worker> getWorker( int... workerId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void addAssignation( Assignation assignation )
			throws DaoException {
		Connection connection = getConnection();

	}

	@Override
	public void updateAssignation( Assignation assignation )
			throws DaoException {
		
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
	public void updateProfession( Profession profession )
			throws DaoException {
		
	}

	@Override
	public void deleteProfession( int professionId ) throws DaoException {
	
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
	public void updateClaimStatus( ClaimStatus claimStatus )
			throws DaoException {
		
	}

	@Override
	public void deleteClaimStatus( ClaimStatus claimStatus )
			throws DaoException {

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
	
	@Override
	public HashMap<String, String> query( String query ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
