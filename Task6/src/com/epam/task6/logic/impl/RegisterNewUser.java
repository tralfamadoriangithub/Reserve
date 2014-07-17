package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;

import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.DataManager;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.logic.ICommand;

public class RegisterNewUser implements ICommand{

	@Override
	public String execute( HttpServletRequest request ) {
		
		String login = request.getParameter( RequestParameterName.LOGIN );
		String password = request.getParameter( RequestParameterName.PASSWORD );
		
		DaoFactory daoFactory = DaoFactory.getInstance();
		DataManager dataManager = daoFactory.getDataManager();
		IAccessManager accessManager = daoFactory.getAccessManager( );
		accessManager.register( login, password );
		return null;
	}

}
