package com.epam.task6.logic;

import com.epam.task6.exception.ProjectLogicException;

public class CommandLogicException extends ProjectLogicException{

	private static final long serialVersionUID = 1L;
	
	public CommandLogicException( String message ) {
		super( message );
	}
	public CommandLogicException( String message, Exception exception ) {
		super( message, exception );
	}
}
