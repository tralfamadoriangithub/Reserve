package com.epam.task6.logic;

import java.util.HashMap;
import java.util.Map;

import com.epam.task6.logic.impl.ChangeLanguageCommand;
import com.epam.task6.logic.impl.DeleteAssignationCommand;
import com.epam.task6.logic.impl.DeleteClaimCommand;
import com.epam.task6.logic.impl.EditClaimCommand;
import com.epam.task6.logic.impl.RegisterAssignationCommand;
import com.epam.task6.logic.impl.CreateClaimCommand;
import com.epam.task6.logic.impl.CreateDatabaseCommand;
import com.epam.task6.logic.impl.DeleteDatabaseCommand;
import com.epam.task6.logic.impl.CreateAssignationCommand;
import com.epam.task6.logic.impl.NoSuchCommand;
import com.epam.task6.logic.impl.GetUserPageCommand;
import com.epam.task6.logic.impl.RegisterNewAddressCommand;
import com.epam.task6.logic.impl.RegisterNewUserCommand;
import com.epam.task6.logic.impl.RegisterClaimCommand;
import com.epam.task6.logic.impl.SignInCommand;
import com.epam.task6.logic.impl.SignOutCommand;
import com.epam.task6.logic.impl.UpdateClaimCommand;

public class CommandHelper {
	
	private static final CommandHelper instance;
	private Map<CommandName, ICommand> commands;
	
	static{
		instance = new CommandHelper();
	}
	
	private CommandHelper(){
		commands = new HashMap<CommandName, ICommand>();
		commands.put( CommandName.CREATE_DATABASE_COMMAND, new CreateDatabaseCommand() );
		commands.put( CommandName.DELETE_DATABASE_COMMAND, new DeleteDatabaseCommand() );
		commands.put( CommandName.REGISTER_NEW_USER_COMMAND, new RegisterNewUserCommand() );
		commands.put( CommandName.GET_USER_PAGE_COMMAND, new GetUserPageCommand() );
		commands.put( CommandName.SIGN_IN_COMMAND, new SignInCommand() );
		commands.put( CommandName.SIGN_OUT_COMMAND, new SignOutCommand() );
		commands.put( CommandName.CHANGE_LANGUAGE_COMMAND, new ChangeLanguageCommand() );
		commands.put( CommandName.CREATE_CLAIM_COMMAND, new CreateClaimCommand() );
		commands.put( CommandName.SEND_CLAIM_COMMAND, new RegisterClaimCommand() );
		commands.put( CommandName.EDIT_CLAIM_COMMAND, new EditClaimCommand() );
		commands.put( CommandName.UPDATE_CLAIM_COMMAND, new UpdateClaimCommand() );
		commands.put( CommandName.DELETE_CLAIM_COMMAND, new DeleteClaimCommand() );
		commands.put( CommandName.REGISTER_NEW_ADDRESS_COMMAND, new RegisterNewAddressCommand() );
		commands.put( CommandName.CREATE_ASSIGNATION_COMMAND, new CreateAssignationCommand() );
		commands.put( CommandName.REGISTER_ASSIGNATION_COMMAND, new RegisterAssignationCommand() );
		commands.put( CommandName.DELETE_ASSIGNATION_COMMAND, new DeleteAssignationCommand() );
		commands.put( CommandName.NO_SUCH_COMMAND, new NoSuchCommand() );
	}
	
	public synchronized static CommandHelper getInstance(){
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