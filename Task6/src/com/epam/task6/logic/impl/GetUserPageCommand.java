package com.epam.task6.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.DataManager;
import com.epam.task6.dao.DataType;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.entity.Address;
import com.epam.task6.entity.Assignation;
import com.epam.task6.entity.Claim;
import com.epam.task6.entity.User;
import com.epam.task6.entity.Worker;
import com.epam.task6.logic.ICommand;

public class GetUserPageCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request, HttpServletResponse response ) {
		String page = null;
		String login = request.getParameter( RequestParameterName.LOGIN );
		String password = request.getParameter( RequestParameterName.PASSWORD );
		DaoFactory daoFactory = DaoFactory.getInstance();
		IAccessManager accessManager = daoFactory.getAccessManager( );
		
		User user = accessManager.signIn( login, password );
		
		if(user != null){
			IDataManager dataManager = daoFactory.getDataManager();
			int userId = user.getUserId();
			switch ( user.getStatus() ) {
			
			case 1:
				loadUserData(userId, request, dataManager );
				page = JspPageName.USER_PAGE;
				break;
				
			case 2:
				loadOperatorData( request, dataManager );
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

	private void loadUserData(int userId, HttpServletRequest request, IDataManager dataManager ){
		List<Address> addresses = dataManager.getUsersAddress( userId );
		List<Claim> claims = dataManager.getUsersClaim( userId );
		HttpSession session = request.getSession();
		session.setAttribute( "addresses", addresses );
		session.setAttribute( "claims", claims );
	}
	
	private void loadOperatorData( HttpServletRequest request, IDataManager dataManager ){
		List<Worker> workers = dataManager.getAllWorkers();
		List<Assignation> assignations = dataManager.getAllAssignations();
		HttpSession session = request.getSession();
		session.setAttribute( "workers", workers );
		session.setAttribute( "assignations", assignations );
	}
	
	private void loadAdminData(  HttpServletRequest request, IDataManager dataManager ){
		
	}
}
