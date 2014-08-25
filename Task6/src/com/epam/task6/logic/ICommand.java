package com.epam.task6.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ��������� ����������� ����� ���������� �������.
 * @author dmitry
 *
 */
public interface ICommand {
	/**
	 * ����� ��������� ������� ����������.
	 * @param request
	 * @param response
	 * @return ����� JSP ��������, ��������������� ������.
	 * @throws CommandException
	 * @throws CommandLogicException
	 */
	public String execute( HttpServletRequest request, HttpServletResponse response ) throws CommandException, CommandLogicException ;
}
