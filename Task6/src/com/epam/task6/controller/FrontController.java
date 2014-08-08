package com.epam.task6.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.task6.dao.DaoException;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandHelper;
import com.epam.task6.logic.ICommand;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger
			.getLogger( com.epam.task6.controller.FrontController.class );
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException {
		handleCommand( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException {
		handleCommand( request, response );
	}

	private void handleCommand( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException {
		
		String page = null;
		ICommand command = CommandHelper.getInstance().getCommand(
				request.getParameter( "command" ) );

		try {
			page = command.execute( request, response );
		} catch ( CommandException e ) {
			//response.sendRedirect( "Task6/error.jsp" );
			log.error( "Error", e.getHiddenException() );
		}
		if( page == null ){
			System.out.println("Page == null");
			page = JspPageName.ERROR_PAGE;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher( page );
		dispatcher.forward( request, response );
	}

}
