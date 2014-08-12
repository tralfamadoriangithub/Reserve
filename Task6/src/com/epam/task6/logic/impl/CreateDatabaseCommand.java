package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.dao.DaoException;
import com.epam.task6.databasehelper.DatabaseHelper;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;

public class CreateDatabaseCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException, CommandLogicException {
		try {
			DatabaseHelper dbHelper = DatabaseHelper.getInstance();
			dbHelper.createDatabase();
		} catch ( DaoException e ) {
			throw new CommandException( "DB creating exception", e );
		}
		return JspPageName.ADMIN_PAGE;
	}

}
