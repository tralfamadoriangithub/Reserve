package com.epam.task6.logic.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.SessionParameterName;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;
/**
* �����, ����������� {@link com.epam.task6.logic.ICommand} ����������� ������� ������ �� �������.
* @author dmitry
*
*/
public class SignOutCommand implements ICommand {

	/**
	 * ����� ��������� ������� �������� �������� �������� �������.
	 * ��������???
	 * @return ����� �������� ����� � �������.
	 */
	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException, CommandLogicException {

		HttpSession session = request.getSession();
		Enumeration<String> attributes = session.getAttributeNames();
		
		while ( attributes.hasMoreElements() ) {
			String attribute = attributes.nextElement();
			if ( !attribute.equals( SessionParameterName.LANGUAGE ) ) {
				session.removeAttribute( attribute );
			}
		}
		// session.invalidate();

		return JspPageName.INDEX_PAGE;
	}

}
