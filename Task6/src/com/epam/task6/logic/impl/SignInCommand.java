package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;
/**
* Класс, реализующий {@link com.epam.task6.logic.ICommand} реализующий команду входа в систему.
* @author dmitry
*
*/
public class SignInCommand implements ICommand{

	/**
	 * @return адрес страницы входа в систему.
	 */
	@Override
	public String execute( HttpServletRequest request, HttpServletResponse response ) throws CommandException, CommandLogicException {
		return JspPageName.LOGIN_PAGE;
	}

}
