package com.epam.task6.dao;

import com.epam.task6.exception.ProjectException;

public class DaoException extends ProjectException{

	public DaoException( String message ) {
		super( message );
	}
	
	public DaoException( String message, Exception exception ) {
		super( message, exception );
	}
}
