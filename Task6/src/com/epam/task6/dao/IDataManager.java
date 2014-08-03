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

public interface IDataManager {

	public HashMap<String, String> query( String query );

	public int addAddress( Address address );

	public boolean updateAddress( Address address );

	public boolean deleteAddress( int addressId );

	public List<Address> getAddress( int... addressId );
	
	public List<Address> getUsersAddress( int ... userId );

	public int addClaim( Claim claim );

	public boolean updateClaim( Claim claim );

	public boolean deleteClaim( int claimId );

	public List<Claim> getClaim( int... claimId );
	
	public List<Claim> getUsersClaim( int ... userId );

	public int addUser( User user );

	public boolean updateUser( User user );

	public boolean deleteUser( int userId );

	public List<User> getUser( int... userId );

	public int addWorker( Worker worker );

	public boolean updateWorker( Worker worker );

	public boolean deleteWorker( int workerId );

	public List<Worker> getWorker( int... workerId );

	public int addAssignation( Assignation assignation );

	public boolean updateAssignation( Assignation assignation );

	public boolean deleteAssignation( int assignationId );

	public List<Assignation> getAssignation( int... assignationId );
	
	public int addProfession( Profession profession );
	public boolean updateProfession( Profession profession );
	public boolean deleteProfession( int professionId );
	public List<Profession> getProfession( int ... professionId );
	public int addClaimStatus( ClaimStatus claimStatus);
	public boolean updateClaimStatus( ClaimStatus claimStatus );
	public boolean deleteClaimStatus( ClaimStatus claimStatus );
	public List<ClaimStatus> getClaimStatus( int ... claimId );

}
