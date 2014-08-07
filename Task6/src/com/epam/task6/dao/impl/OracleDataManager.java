package com.epam.task6.dao.impl;

import java.util.HashMap;
import java.util.List;

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

public class OracleDataManager implements IDataManager {

	private static OracleDataManager instance;

	private OracleDataManager() {
		// TODO Auto-generated constructor stub
	}

	public static OracleDataManager getInstance() {
		if ( null == instance ) {
			instance = new OracleDataManager();
		}
		return instance;
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
	public int addClaim( Claim claim ) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
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
	public int addUser( User user ) throws DaoException {
		// TODO Auto-generated method stub
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
	public List<Address> getUsersAddress( int... userId ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClaimTableEntity> getUsersClaim( User... user ) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> getAllAddresses() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClaimTableEntity> getAllClaims() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Worker> getAllWorkers() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assignation> getAllAssignations() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profession> getAllProfessions() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClaimStatus> getAllClaimStatuses() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
