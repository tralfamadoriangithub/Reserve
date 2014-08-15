package com.epam.task6.logic.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.controller.SessionParameterName;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;

public class ChangeLanguageCommand implements ICommand {

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException, CommandLogicException {
		HttpSession session = request.getSession();

		String selectedLanguage = request
				.getParameter( RequestParameterName.LANGUAGE );
		
		if ( selectedLanguage.equals( "ru_RU" ) ) {
			session.setAttribute( SessionParameterName.LANGUAGE, new Locale( "ru", "RU" ) );
			Locale.setDefault( new Locale( "ru", "RU" ) );
		} else if ( selectedLanguage.equals( "en_US" ) ) {
			session.setAttribute( SessionParameterName.LANGUAGE, new Locale( "en", "US" ) );
			Locale.setDefault( new Locale( "en", "US" ) );
		}
		String page = (String) session.getAttribute( SessionParameterName.CURRENT_PAGE );
		return page.replace( "/Task6", "" );
	}

}
