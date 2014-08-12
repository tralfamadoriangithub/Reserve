package com.epam.task6.exception;

public class ProjectException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Exception hiddenException;

	public ProjectException( String message ) {
		super( message );
	}

	public ProjectException( String message, Exception exception ) {
		super( message );
		hiddenException = exception;
	}

	public Exception getHiddenException() {
		return hiddenException;
	}
}
