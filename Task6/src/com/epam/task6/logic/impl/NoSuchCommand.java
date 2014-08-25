package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;
/**
* Класс, реализующий {@link com.epam.task6.logic.ICommand} реагирующий на попытку
* выполнения некорректной команды
* @author dmitry
*
*/
public class NoSuchCommand implements ICommand{

	/**
	 * @return адрес страницы ошибки.
	 */
	@Override
	public String execute( HttpServletRequest request, HttpServletResponse response ) throws CommandException, CommandLogicException {
		return JspPageName.ERROR_PAGE;
	}

}
