package com.epam.task6.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.logic.CommandHelper;
import com.epam.task6.logic.ICommand;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
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
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute( "currentPage" ));
		ICommand command = CommandHelper.getInstance().getCommand(
				request.getParameter( "command" ) );
		//System.out.println( command );
		String page = null;
		if ( null != command ) {
			page = command.execute( request, response );
		} else {
			page = JspPageName.ERROR_PAGE;
		}
		//System.out.println( page );
		System.out.println(session.getAttribute( "currentPage" ));
		RequestDispatcher dispatcher = request.getRequestDispatcher( page );
		dispatcher.forward( request, response );
	}

}
