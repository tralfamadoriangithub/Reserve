package com.epam.task6.dao;

import java.util.HashMap;
import java.util.List;

import com.epam.task6.entity.Address;
import com.epam.task6.entity.Assignation;
import com.epam.task6.entity.Claim;
import com.epam.task6.entity.ClaimStatus;
import com.epam.task6.entity.Profession;
import com.epam.task6.entity.User;
import com.epam.task6.entity.Worker;
import com.epam.task6.tableentity.ClaimTableEntity;
import com.epam.task6.tableentity.WorkerTableEntity;

public interface IDataDao {

	public HashMap<String, String> query( String query ) throws DaoException;

	public int addAddress( Address address ) throws DaoException;

	public boolean updateAddress( Address address ) throws DaoException;

	public boolean deleteAddress( int addressId ) throws DaoException;

	public List<Address> getAddress( int... addressId ) throws DaoException;
	
	public List<Address> getAllAddresses() throws DaoException;

	public List<Address> getUsersAddress( int... userId ) throws DaoException;

	public int addClaim( Claim claim ) throws DaoException;

	public boolean updateClaim( Claim claim ) throws DaoException;

	public boolean deleteClaim( int claimId ) throws DaoException;

	public List<Claim> getClaim( int... claimId ) throws DaoException;
	
	public List<ClaimTableEntity> getAllClaims( ) throws DaoException;

	public List<ClaimTableEntity> getUsersClaim( User... user ) throws DaoException;

	public int addUser( User user ) throws DaoException;

	public boolean updateUser( User user ) throws DaoException;

	public boolean deleteUser( int userId ) throws DaoException;

	public List<User> getUser( int... userId ) throws DaoException;
	
	public List<User> getAllUsers() throws DaoException;

	public int addWorker( Worker worker ) throws DaoException;

	public boolean updateWorker( Worker worker ) throws DaoException;

	public boolean deleteWorker( int workerId ) throws DaoException;

	public List<Worker> getWorker( int... workerId ) throws DaoException;
	
	public List<WorkerTableEntity> getAllWorkers() throws DaoException;

	public int addAssignation( Assignation assignation ) throws DaoException;

	public boolean updateAssignation( Assignation assignation ) throws DaoException;

	public boolean deleteAssignation( int assignationId ) throws DaoException;

	public List<Assignation> getAssignation( int... assignationId ) throws DaoException;
	
	public List<Assignation> getAllAssignations() throws DaoException;

	public int addProfession( Profession profession ) throws DaoException;

	public boolean updateProfession( Profession profession ) throws DaoException;

	public boolean deleteProfession( int professionId ) throws DaoException;

	public List<Profession> getProfession( int... professionId ) throws DaoException;
	
	public List<Profession> getAllProfessions() throws DaoException;

	public int addClaimStatus( ClaimStatus claimStatus ) throws DaoException;

	public boolean updateClaimStatus( ClaimStatus claimStatus ) throws DaoException;

	public boolean deleteClaimStatus( ClaimStatus claimStatus ) throws DaoException;

	public List<ClaimStatus> getClaimStatus( int... claimId ) throws DaoException;
	
	public List<ClaimStatus> getAllClaimStatuses() throws DaoException;

}
