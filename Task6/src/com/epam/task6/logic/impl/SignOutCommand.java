package com.epam.task6.logic.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.ICommand;

public class SignOutCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException {
		
		HttpSession session = request.getSession();
		
		Enumeration<String> attributes = session.getAttributeNames();
		while(attributes.hasMoreElements()){
			session.removeAttribute( attributes.nextElement() );
		}
		session.invalidate();
		
		return JspPageName.INDEX_PAGE;
	}

}
