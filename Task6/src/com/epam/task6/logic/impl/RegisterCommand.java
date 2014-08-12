package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;

public class RegisterCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request, HttpServletResponse response ) throws CommandException, CommandLogicException {
		return JspPageName.REGISTER_PAGE;
	}

}
