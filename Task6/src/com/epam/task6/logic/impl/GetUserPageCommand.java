package com.epam.task6.logic.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.DataType;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.entity.Address;
import com.epam.task6.entity.Assignation;
import com.epam.task6.entity.Claim;
import com.epam.task6.entity.User;
import com.epam.task6.entity.Worker;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.ICommand;
import com.epam.task6.tableentity.ClaimTableEntity;

public class GetUserPageCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request, HttpServletResponse response ) throws CommandException {
		String page = null;
		User user = null;
		String login = request.getParameter( RequestParameterName.LOGIN );
		String password = request.getParameter( RequestParameterName.PASSWORD );
		DaoFactory daoFactory = DaoFactory.getInstance();
		IAccessManager accessManager = daoFactory.getAccessManager( );
		
		try {
			user = accessManager.signIn( login, password );
		} catch ( DaoException e ) {
			throw new CommandException( "Exception in \"GetUserPageCommand\"", e );
		}
		
		if(user != null){
			IDataManager dataManager = daoFactory.getDataManager();
			switch ( user.getStatus() ) {
			
			case 1:
				try {
					loadUserData(user, request, dataManager );
				} catch ( DaoException e ) {
					throw new CommandException( "Exception in \"GetUserPageCommand\"", e );
				}
				page = JspPageName.USER_PAGE;
				break;
				
			case 2:
				try {
					loadOperatorData( request, dataManager );
				} catch ( DaoException e ) {
					throw new CommandException( "Exception in \"GetUserPageCommand\"", e );
				}
				page = JspPageName.OPERATOR_PAGE;
				break;
				
			case 3:
				loadAdminData( request, dataManager );
				page = JspPageName.ADMIN_PAGE;
				break;

			default:
				page = JspPageName.LOGIN_PAGE;
				break;
			}
			request.getSession().setAttribute( "login", true );
			request.getSession().setAttribute( "user", user );
		}else{
			page = JspPageName.LOGIN_PAGE;
		}
		
		return page;
	}

	private void loadUserData(User user, HttpServletRequest request, IDataManager dataManager ) throws DaoException{
		List<Address> addresses = dataManager.getUsersAddress( user.getUserId() );
		List<ClaimTableEntity> claims = dataManager.getUsersClaim( user );
		HttpSession session = request.getSession();
		session.setAttribute( "addresses", addresses );
		session.setAttribute( "claims", claims );
	}
	
	private void loadOperatorData( HttpServletRequest request, IDataManager dataManager ) throws DaoException{
		List<Worker> workers = dataManager.getAllWorkers();
		List<Assignation> assignations = dataManager.getAllAssignations();
		List<ClaimTableEntity> claims = dataManager.getAllClaims();
		
		HttpSession session = request.getSession();
		session.setAttribute( "claims", claims );
		session.setAttribute( "workers", workers );
		session.setAttribute( "assignations", assignations );
	}
	
	private void loadAdminData(  HttpServletRequest request, IDataManager dataManager ){
		
	}
}
