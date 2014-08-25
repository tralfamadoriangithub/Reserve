package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.controller.SessionParameterName;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.IDataDao;
import com.epam.task6.entity.User;
import com.epam.task6.entity.UserStatusValues;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;
/**
* Класс, реализующий {@link com.epam.task6.logic.ICommand} реализующий команду регистрации
* нового обычного пользователя.
* @author dmitry
*
*/
public class RegisterNewUserCommand implements ICommand {

	/**
	 * @return адрес страницы пользователя.
	 */
	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException, CommandLogicException {
		
		HttpSession session = request.getSession();
		User newUser = createUser( request );
		
		try {
			IDataDao dataManager = DaoFactory.getInstance().getDataDao();
			dataManager.addUser( newUser );
		} catch ( DaoException e ) {
			throw new CommandException(
					"Exception in \"RegisterNewUserCommand\"", e );
		}
		session.setAttribute( SessionParameterName.USER, newUser );
		session.setAttribute( SessionParameterName.IS_LOGIN, true );
		return JspPageName.USER_PAGE;
	}

	private User createUser( HttpServletRequest request ) {

		User user = new User();
		user.setLogin( request.getParameter( RequestParameterName.LOGIN ) );
		user.setPassword( request.getParameter( RequestParameterName.PASSWORD ) );
		user.setName( request.getParameter( RequestParameterName.NAME ) );
		user.setSurname( request.getParameter( RequestParameterName.SURNAME ) );
		user.setStatus( UserStatusValues.REGULAR_USER );

		return user;
	}

}
