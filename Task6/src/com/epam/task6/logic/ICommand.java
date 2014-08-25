package com.epam.task6.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * »нтерфейс объ€вл€ющий метод выполнени€ команды.
 * @author dmitry
 *
 */
public interface ICommand {
	/**
	 * ћетод обработки команды приложени€.
	 * @param request
	 * @param response
	 * @return адрес JSP страницы, соответствующей логике.
	 * @throws CommandException
	 * @throws CommandLogicException
	 */
	public String execute( HttpServletRequest request, HttpServletResponse response ) throws CommandException, CommandLogicException ;
}
