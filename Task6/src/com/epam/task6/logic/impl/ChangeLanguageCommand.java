package com.epam.task6.logic.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.ICommand;

public class ChangeLanguageCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request, HttpServletResponse response ) throws CommandException {
		HttpSession session = request.getSession();
		
		if(request.getParameter ( "lang" ).equals( "ru" )){
			System.out.println("equ ru");
			request.setAttribute( "language", "ru_RU" );
		}else if(request.getParameter( "lang" ).equals( "en" )){
			System.out.println("equ en");
			request.setAttribute( "language", Locale.US );
		}
		String page = (String) session.getAttribute( "currentPage" );
		return page.replace( "/Task6", "" );
	}

}
