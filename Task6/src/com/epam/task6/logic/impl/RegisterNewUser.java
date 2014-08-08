package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.entity.User;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.ICommand;

public class RegisterNewUser implements ICommand {

	@Override
	public String execute( HttpServletRequest request, HttpServletResponse response ) throws CommandException {

		User newUser = createUser( request );
		IDataManager dataManager = DaoFactory.getInstance().getDataManager();
		try {
			dataManager.addUser( newUser );
		} catch ( DaoException e ) {
			throw new CommandException( "Exception in \"RegisterNewUserCommand\"", e );
		}
		if ( newUser.getUserId() == -1 ) {
			return JspPageName.ERROR_PAGE;
		} else {
			request.getSession().setAttribute( "user", newUser );
			request.getSession().setAttribute( "login", true );
			return JspPageName.USER_PAGE;
		}
	}

	private User createUser( HttpServletRequest request ) {

		User user = new User();
		user.setLogin( request.getParameter( RequestParameterName.LOGIN ) );
		user.setPassword( request.getParameter( RequestParameterName.PASSWORD ) );
		user.setName( request.getParameter( RequestParameterName.NAME ) );
		user.setSurname( request.getParameter( RequestParameterName.SURNAME ) );
		user.setStatus( 1 );

		return user;
	}

}
