package com.epam.task6.logic.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.controller.SessionParameterName;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.ICommand;

public class ChangeLanguageCommand implements ICommand {

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException {
		HttpSession session = request.getSession();

		String selectedLanguage = request
				.getParameter( RequestParameterName.LANGUAGE );
		
		if ( selectedLanguage.equals( "ru" ) ) {
			request.setAttribute( RequestParameterName.LANGUAGE, "ru" );
		} else if ( selectedLanguage.equals( "en" ) ) {
			request.setAttribute( RequestParameterName.LANGUAGE, "en" );
		}
		String page = (String) session.getAttribute( SessionParameterName.CURRENT_PAGE );
		return page.replace( "/Task6", "" );
	}

}
