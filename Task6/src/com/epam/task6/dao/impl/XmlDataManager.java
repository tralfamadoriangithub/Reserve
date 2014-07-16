package com.epam.task6.dao.impl;

import java.util.HashMap;

import com.epam.task6.dao.DataManager;
import com.epam.task6.dao.IDataManager;

public class XmlDataManager extends DataManager {

	private static XmlDataManager instance;

	private XmlDataManager() {
		// TODO Auto-generated constructor stub
	}

	public static XmlDataManager getInstance() {
		if ( null == instance ) {
			instance = new XmlDataManager();
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
