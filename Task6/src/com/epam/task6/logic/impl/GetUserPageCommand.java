package com.epam.task6.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.DataManager;
import com.epam.task6.dao.DataType;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.entity.Address;
import com.epam.task6.entity.Claim;
import com.epam.task6.entity.User;
import com.epam.task6.logic.ICommand;

public class GetUserPageCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request ) {
		String page = null;
		String login = request.getParameter( RequestParameterName.LOGIN );
		String password = request.getParameter( RequestParameterName.PASSWORD );
		DaoFactory daoFactory = DaoFactory.getInstance();
		IAccessManager accessManager = daoFactory.getAccessManager( );
		User user = accessManager.signIn( login, password );
		
		if(user != null){
			
			switch ( user.getStatus() ) {
			
			case 1:
				loadUserData( request );
				page = JspPageName.USER_PAGE;
				break;
				
			case 2:
				loadOperatorData( request );
				page = JspPageName.OPERATOR_PAGE;
				break;
				
			case 3:
				loadAdminData( request );
				page = JspPageName.ADMIN_PAGE;
				break;

			default:
				page = JspPageName.ERROR_PAGE;
				break;
			}
			request.setAttribute( "isIogin", true );
			request.setAttribute( "user", user );
		}else{
			page = JspPageName.ERROR_PAGE;
		}
		
		return page;
	}

	private void loadUserData( HttpServletRequest request ){
		List<Address> addresses = new ArrayList<>();
		List<Claim> claims = new ArrayList<>();
	}
	
	private void loadOperatorData( HttpServletRequest request ){
		
	}
	
	private void loadAdminData(  HttpServletRequest request ){
		
	}
}
