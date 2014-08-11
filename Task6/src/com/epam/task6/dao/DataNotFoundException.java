package com.epam.task6.dao;

import com.epam.task6.exception.ProjectLogicException;

public class DataNotFoundException extends ProjectLogicException{

	private static final long serialVersionUID = 1L;

	public DataNotFoundException( String message ) {
		super( message );
	}
	
	public DataNotFoundException( String message, Exception exception ) {
		super( message, exception );
	}

}
