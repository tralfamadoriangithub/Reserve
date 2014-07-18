package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;

import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.DataManager;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.entity.User;
import com.epam.task6.logic.ICommand;

public class RegisterNewUser implements ICommand{

	@Override
	public String execute( HttpServletRequest request ) {
		
		User newUser = createUser( request );
		
		IAccessManager accessManager = DaoFactory.getInstance().getAccessManager( );
		
		
		
		return null;
	}
	
	private User createUser(HttpServletRequest request){
		
		User user = new User();
		
		user.setLogin( request.getParameter( RequestParameterName.LOGIN ) );
		user.setPassword( request.getParameter( RequestParameterName.PASSWORD ) );
		user.setName( request.getParameter( RequestParameterName.NAME ) );
		user.setSurname( request.getParameter( RequestParameterName.SURNAME ) );
		user.setStatus( 1 );
		
		return user;
	}

}
