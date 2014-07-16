package com.epam.task6.dao.impl;

import com.epam.task6.dao.IAccessManager;
import com.epam.task6.entity.User;

public class JsonAccessManager implements IAccessManager{

    private static JsonAccessManager instance;
	
	private JsonAccessManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static JsonAccessManager getInstance(){
		if(instance == null){
			instance = new JsonAccessManager();
		}
		return instance;
	}
	
	@Override
	public User signIn( String user, String password ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void signOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register( String user, String password ) {
		// TODO Auto-generated method stub
		
	}

}
