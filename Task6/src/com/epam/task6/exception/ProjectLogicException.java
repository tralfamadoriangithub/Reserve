package com.epam.task6.exception;

public class ProjectLogicException extends Exception{
	private Exception hiddenException;

	public ProjectLogicException( String message ) {
		super( message );
	}

	public ProjectLogicException( String message, Exception exception ) {
		super( message );
		hiddenException = exception;
	}

	public Exception getHiddenException() {
		return hiddenException;
	}
}
