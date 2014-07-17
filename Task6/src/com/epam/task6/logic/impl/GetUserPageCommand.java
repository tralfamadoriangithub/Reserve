package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.DataManager;
import com.epam.task6.dao.DataType;
import com.epam.task6.dao.IAccessManager;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.entity.User;
import com.epam.task6.logic.ICommand;

public class GetUserPageCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request ) {
		String page = null;
		String login = request.getParameter( RequestParameterName.LOGIN );
		String password = request.getParameter( RequestParameterName.PASSWORD );
		System.out.println("GET USER PAGE");
		DaoFactory daoFactory = DaoFactory.getInstance();
		IAccessManager accessManager = daoFactory.getAccessManager( );
		User user = accessManager.signIn( login, password );
		
		if(user != null){
			switch ( user.getStatus() ) {
			
			case 1:
				page = JspPageName.USER_PAGE;
				break;
				
			case 2:
				page = JspPageName.OPERATOR_PAGE;
				break;
				
			case 3:
				
				break;

			default:
				page = JspPageName.ERROR_PAGE;
				break;
			}
			request.setAttribute( "user", user );
		}else{
			page = JspPageName.ERROR_PAGE;
		}
		
		return page;
	}

}
