package com.epam.task6.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRequestFilter implements Filter {

	private FilterConfig filterConfig = null;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter( ServletRequest request, ServletResponse response,
			FilterChain chain ) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpRequest.setCharacterEncoding( "UTF-8" );
		httpResponse.setCharacterEncoding( "UTF-8" );
		httpResponse.setContentType( "text/html; charset=UTF-8" );

		httpResponse.setHeader( "Cache-Control", "no-cache" );
		httpResponse.setHeader( "Pragma", "no-cache" );
		httpResponse.setDateHeader( "Expires", 0 );

		chain.doFilter( request, response );
	}

	@Override
	public void init( FilterConfig filterConfig ) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
