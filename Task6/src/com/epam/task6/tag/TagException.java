package com.epam.task6.tag;

import com.epam.task6.exception.ProjectException;

public class TagException extends ProjectException{

	private static final long serialVersionUID = 1L;

	public TagException( String message ) {
		super( message );
	}
	
	public TagException( String message, Exception exception ) {
		super( message, exception );
	}
}
