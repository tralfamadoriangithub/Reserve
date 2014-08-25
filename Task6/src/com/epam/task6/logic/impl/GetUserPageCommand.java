package com.epam.task6.logic.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.controller.SessionParameterName;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.DataNotFoundException;
import com.epam.task6.dao.IAccessDao;
import com.epam.task6.dao.IDataDao;
import com.epam.task6.entity.Address;
import com.epam.task6.entity.User;
import com.epam.task6.entity.UserStatusValues;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;
import com.epam.task6.tableentity.AssignationTableEntity;
import com.epam.task6.tableentity.ClaimTableEntity;
import com.epam.task6.tableentity.WorkerTableEntity;
/**
* Класс, реализующий {@link com.epam.task6.logic.ICommand} реализующий команду входа пользователя
* в систему.
* 
* Класс реализует вход пользователя в систему и получение соответствующих типу пользователя страницы и 
* данных. Перегружен функционалом и несоответствующей названию класса логикой. Необходимо исправить.
* @author dmitry
*
*/
public class GetUserPageCommand implements ICommand {

	/**
	 * @return адрес страницы соответствующей типу пользователя.
	 */
	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException, CommandLogicException {
		String page = null;
		User user = null;
		HttpSession session = request.getSession();
		String login = request.getParameter( RequestParameterName.LOGIN );
		String password = request.getParameter( RequestParameterName.PASSWORD );

		// ////////////////////////// DELETE THIS
		// /////////////////////////////////////

		if ( login.equals( "admin" ) && password.equals( "admin" ) ) {
			return JspPageName.ADMIN_PAGE;
		}

		// ///////////////////////////////////////////////////////////////////////////////////////

		DaoFactory daoFactory = DaoFactory.getInstance();
		
		try {
			IAccessDao accessManager = daoFactory.getAccessDao();
			user = accessManager.signIn( login, password );
			IDataDao dataDao = daoFactory.getDataDao();
			page = getPageForUser( user );
			loadUserData( user, dataDao, session );
			session.setAttribute( SessionParameterName.IS_LOGIN, true );
			session.setAttribute( SessionParameterName.USER, user );
			session.setAttribute( SessionParameterName.USER_PAGE, page );
		} catch ( DaoException e ) {
			throw new CommandException( "Exception in \"GetUserPageCommand\"",
					e );
		} catch ( DataNotFoundException e ) {
			page = JspPageName.LOGIN_PAGE;
			request.setAttribute( RequestParameterName.MESSAGE,
					"Wrong login or password" );
		}
		return page;
	}
	/**
	 * Метод, возвращающий адрес страницы, соответствующий типу пользователя.
	 * @param user объект пользователя
	 * @return адрес страницы пользователя
	 * @throws DaoException
	 */
	private String getPageForUser( User user ) throws DaoException {
		String page;

		switch ( user.getStatus() ) {

		case UserStatusValues.REGULAR_USER:
			page = JspPageName.USER_PAGE;
			break;

		case UserStatusValues.OPERATOR:
			page = JspPageName.OPERATOR_PAGE;
			break;

		case UserStatusValues.ADMINISTRATOR:
			page = JspPageName.ADMIN_PAGE;
			break;

		default:
			page = JspPageName.LOGIN_PAGE;
			break;
		}
		
		return page;
	}
	/**
	 * Метод, загружающий данные, соответствующие типу пользователя
	 * @param user объект пользователя
	 * @param dataDao объект взаимодействия с источником данных
	 * @param session текущая сессия
	 * @throws DaoException
	 */
	private void loadUserData( User user, IDataDao dataDao, HttpSession session ) throws DaoException {
		switch ( user.getStatus() ) {

		case UserStatusValues.REGULAR_USER:
			loadRegularUserData( user, session, dataDao );
			break;

		case UserStatusValues.OPERATOR:
			loadOperatorData( session, dataDao );
			break;

		case UserStatusValues.ADMINISTRATOR:
			loadAdminData( session, dataDao );
			break;

		default:
			
			break;
		}
	}

	/**
	 * Метод, загружающий данные, соответствующие типу обычного пользователя
	 * @param user объект пользователя
	 * @param dataDao объект взаимодействия с источником данных
	 * @param session текущая сессия
	 * @throws DaoException
	 */
	private void loadRegularUserData( User user, HttpSession session,
			IDataDao dataDao ) throws DaoException {
		List<Address> addresses = dataDao
				.getUsersAddress( user.getUserId() );
		List<ClaimTableEntity> claims = dataDao.getUsersClaim( user );
		session.setAttribute( SessionParameterName.ADDRESSES, addresses );
		session.setAttribute( SessionParameterName.CLAIMS, claims );
	}

	/**
	 * Метод, загружающий данные, соответствующие типу пользователя-оператора
	 * @param dataDao объект взаимодействия с источником данных
	 * @param session текущая сессия
	 * @throws DaoException
	 */
	private void loadOperatorData( HttpSession session,
			IDataDao dataDao ) throws DaoException {

		List<WorkerTableEntity> workers = dataDao.getAllWorkers();
		List<AssignationTableEntity> assignations = dataDao.getAllAssignations();
		List<ClaimTableEntity> claims = dataDao.getAllClaims();

		session.setAttribute( SessionParameterName.CLAIMS, claims );
		session.setAttribute( SessionParameterName.WORKERS, workers );
		session.setAttribute( SessionParameterName.ASSIGNATIONS, assignations );
	}

	/**
	 * Метод, загружающий данные, соответствующие типу пользователя-администратора
	 * @param dataDao объект взаимодействия с источником данных
	 * @param session текущая сессия
	 * @throws DaoException
	 */
	private void loadAdminData( HttpSession session, IDataDao dataDao )
			throws DaoException {
	}
}
