package com.epam.task6.dao;

public final class DBField {
	
	private DBField(){}

//		USER TABLE	
	
	public static final String USER_ID = "user_id";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String NAME = "name";
	public static final String SURNAME = "surname";
	public static final String USER_STATUS = "status";
	
//		ADDRESS TABLE	
	
	public static final String ADDRESS_ID = "address_id";
	public static final String STREET = "street";
	public static final String HOUSE = "house";
	public static final String BLOCK = "block";
	public static final String FLAT = "flat";
	public static final String PHONE = "phone";
	
//		CLAIM TABLE
	
	public static final String CLAIM_ID = "claim_id";
	public static final String PROBLEM_DESCRIPTION = "problem_description";
	
//		CLAIM_STATUS TABLE
	
	public static final String CLAIM_STATUS_ID = "claim_status_id";
	public static final String CLAIM_STATUS_VALUE = "claim_status_value";
	
//		WORKER TABLE
	
	public static final String WORKER_ID = "worker_id";
	public static final String WORKER_NAME = "worker_name";
	public static final String WORKER_SURNAME = "worker_surname";
	public static final String QUALIFICATION = "qualification";
	
//		PROFESSION TABLE
	
	public static final String PROFESSION_ID = "profession_id";
	public static final String PROFESSION = "profession";
	
//		ASSIGNATION TABLE
	
	public static final String ASSIGNATION_ID = "assignation_id";
	public static final String BEGIN_WORK = "begin_work";
	public static final String END_WORK = "end_work";
	
//		WORKER_ASSIGNATION TABLE
	
	public static final String WORKER_ASSIGNATION_ID = "worker_assignation_id";
	
}
