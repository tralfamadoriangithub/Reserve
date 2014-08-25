package com.epam.task6.logic;
import com.epam.task6.exception.ProjectException;

public class CommandException extends ProjectException{

	private static final long serialVersionUID = 1L;

	public CommandException( String message ) {
		super( message );
	}
	
	public CommandException( String message, Exception exception ) {
		super( message, exception );
	}
}
