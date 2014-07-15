package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;

import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.logic.ICommand;

public class RegisterNewUser implements ICommand{

	@Override
	public String execute( HttpServletRequest request ) {
		IDataManager dataManager = DaoFactory.getInstance().getDataManager();
	
		return null;
	}

}
