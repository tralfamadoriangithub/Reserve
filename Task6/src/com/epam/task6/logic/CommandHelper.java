package com.epam.task6.logic;

import java.util.HashMap;
import java.util.Map;

import com.epam.task6.logic.impl.NoSuchCommand;
import com.epam.task6.logic.impl.RegisterCommand;
import com.epam.task6.logic.impl.GetUserPageCommand;
import com.epam.task6.logic.impl.SignInCommand;

public class CommandHelper {
	
	private static final CommandHelper instance;
	private Map<CommandName, ICommand> commands;
	
	static{
		instance = new CommandHelper();
	}
	
	private CommandHelper(){
		commands = new HashMap<CommandName, ICommand>();
		commands.put( CommandName.REGISTER_COMMAND, new RegisterCommand() );
		commands.put( CommandName.GET_USER_PAGE_COMMAND, new GetUserPageCommand() );
		commands.put( CommandName.SIGN_IN_COMMAND, new SignInCommand() );
		commands.put( CommandName.NO_SUCH_COMMAND, new NoSuchCommand() );
	}
	
	public static CommandHelper getInstance(){
		return instance;
	}
	
	public ICommand getCommand( String commandName ){
		CommandName name = CommandName.valueOf( commandName.toUpperCase() );
		ICommand command = null;
		
		if( null != name ){
			command = commands.get( name );
		}else{
			command = commands.get( CommandName.NO_SUCH_COMMAND );
		}
		return command;
	}
}