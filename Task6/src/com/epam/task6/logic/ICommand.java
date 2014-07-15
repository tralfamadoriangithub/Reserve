package com.epam.task6.logic;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
	public String execute( HttpServletRequest request );
}
