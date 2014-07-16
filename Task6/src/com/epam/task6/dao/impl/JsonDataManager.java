package com.epam.task6.dao.impl;

import java.util.HashMap;

import com.epam.task6.dao.DataManager;

public class JsonDataManager extends DataManager {

	private static JsonDataManager instance;

	private JsonDataManager() {
		// TODO Auto-generated constructor stub
	}

	public static JsonDataManager getInstance() {
		if ( null == instance ) {
			instance = new JsonDataManager();
		}
		return instance;
	}

	@Override
	public HashMap<String, String> query( String query ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update( String query ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete( String query ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert( String query ) {
		// TODO Auto-generated method stub

	}
}
