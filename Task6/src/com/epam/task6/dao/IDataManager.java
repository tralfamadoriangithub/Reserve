package com.epam.task6.dao;

import java.util.HashMap;

public interface IDataManager {

	public HashMap<String, String> query( String query );
	public void addAddress( String street, int house, int block, int flat, int userId );
	public void deleteAddress( int addressId );
	public void getAddress();
	public void addClaim( int addressId, int userId, String problemDescription );
	public void getClaim();
	public void addUser();
	public void deleteUser();
	public void getUser();
	public void setClaimStatus();
	public void deleteClaim();
	public void createSquad();
	public void deleteSquad();
	public void createAssignment();
	public void addWorker();
	public void deleteWorker();
	
}
