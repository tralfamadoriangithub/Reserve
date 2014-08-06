package com.epam.task6.logic;

import com.epam.task6.exception.ProjectException;

public class CommandException extends ProjectException{

	public CommandException( String message ) {
		super( message );
	}
	
	public CommandException( String message, Exception exception ) {
		super( message, exception );
	}
}
