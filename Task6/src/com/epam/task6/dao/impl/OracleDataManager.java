package com.epam.task6.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.epam.task6.dao.DataManager;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.entity.Address;
import com.epam.task6.entity.Assignation;
import com.epam.task6.entity.Claim;
import com.epam.task6.entity.ClaimStatus;
import com.epam.task6.entity.Profession;
import com.epam.task6.entity.User;
import com.epam.task6.entity.Worker;

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
	public int addUser( User user ) {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public List<Address> getUsersAddress( int... userId ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Claim> getUsersClaim( int... userId ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> getAllAddresses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Claim> getAllClaims() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Worker> getAllWorkers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assignation> getAllAssignations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profession> getAllProfessions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClaimStatus> getAllClaimStatuses() {
		// TODO Auto-generated method stub
		return null;
	}

}
