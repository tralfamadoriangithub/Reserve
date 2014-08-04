package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.logic.ICommand;

public class NoSuchCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request, HttpServletResponse response ) {
		return JspPageName.ERROR_PAGE;
	}

}
