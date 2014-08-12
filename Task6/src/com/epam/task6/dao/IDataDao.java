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
import com.epam.task6.tableentity.AssignationTableEntity;
import com.epam.task6.tableentity.ClaimTableEntity;
import com.epam.task6.tableentity.WorkerTableEntity;

public interface IDataDao {

	public HashMap<String, String> query( String query ) throws DaoException;

	public void addAddress( Address address ) throws DaoException;

	public void updateAddress( Address address ) throws DaoException;

	public void deleteAddress( int addressId ) throws DaoException;

	public List<Address> getAddress( int... addressId ) throws DaoException;

	public List<Address> getAllAddresses() throws DaoException;

	public List<Address> getUsersAddress( int... userId ) throws DaoException;

	public void addClaim( Claim claim ) throws DaoException;

	public void updateClaim( Claim claim ) throws DaoException;

	public void deleteClaim( int claimId ) throws DaoException;

	public List<Claim> getClaim( int... claimId ) throws DaoException;

	public List<ClaimTableEntity> getAllClaims() throws DaoException;

	public List<ClaimTableEntity> getUsersClaim( User... user )
			throws DaoException;

	public void addUser( User user ) throws DaoException;

	public void updateUser( User user ) throws DaoException;

	public void deleteUser( int userId ) throws DaoException;

	public List<User> getUser( int... userId ) throws DaoException;

	public List<User> getAllUsers() throws DaoException;

	public void addWorker( Worker worker ) throws DaoException;

	public void updateWorker( Worker worker ) throws DaoException;

	public void deleteWorker( int workerId ) throws DaoException;

	public List<Worker> getWorker( int... workerId ) throws DaoException;

	public List<WorkerTableEntity> getAllWorkers() throws DaoException;

	public void addAssignation( Assignation assignation ) throws DaoException;

	public void updateAssignation( Assignation assignation )
			throws DaoException;

	public void deleteAssignation( int assignationId ) throws DaoException;

	public List<Assignation> getAssignation( int... assignationId )
			throws DaoException;

	public List<AssignationTableEntity> getAllAssignations()
			throws DaoException;

	public void addProfession( Profession profession ) throws DaoException;

	public void updateProfession( Profession profession )
			throws DaoException;

	public void deleteProfession( int professionId ) throws DaoException;

	public List<Profession> getProfession( int... professionId )
			throws DaoException;

	public List<Profession> getAllProfessions() throws DaoException;

	public void addClaimStatus( ClaimStatus claimStatus ) throws DaoException;

	public void updateClaimStatus( ClaimStatus claimStatus )
			throws DaoException;

	public void deleteClaimStatus( ClaimStatus claimStatus )
			throws DaoException;

	public List<ClaimStatus> getClaimStatus( int... claimId )
			throws DaoException;

	public List<ClaimStatus> getAllClaimStatuses() throws DaoException;

	public void registerNewAssignation( Assignation assignation,
			int... workersId ) throws DaoException;

}
