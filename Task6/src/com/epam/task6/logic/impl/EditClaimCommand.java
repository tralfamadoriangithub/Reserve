package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.controller.SessionParameterName;
import com.epam.task6.entity.Address;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;
import com.epam.task6.tableentity.ClaimTableEntity;
/**
* Класс, реализующий {@link com.epam.task6.logic.ICommand} реализующий команду редактирования
* заявки пользователем.
* @author dmitry
*
*/
public class EditClaimCommand implements ICommand{

	/**
	 * @return адрес страницы пользователя системы.
	 */
	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException,
			CommandLogicException {
		
		HttpSession session = request.getSession();
		Address address = new Address();
		ClaimTableEntity claim = new ClaimTableEntity();
		
		address.setStreet( request.getParameter( RequestParameterName.STREET ) );
		address.setHouseNumber( Integer.valueOf( request.getParameter( RequestParameterName.HOUSE ) ) );
		address.setBlockNumber( Integer.valueOf( request.getParameter( RequestParameterName.BLOCK ) ) );
		address.setFlatNumber( Integer.valueOf( request.getParameter( RequestParameterName.FLAT ) ) );
		
		claim.setAddress( address );
		claim.setClaimId( Integer.valueOf( request.getParameter( RequestParameterName.CLAIM_ID ) ) );
		claim.setProblemDescription( request.getParameter( RequestParameterName.PROBLEM_DESCRIPTION ) );
		
		session.setAttribute( SessionParameterName.CLAIM_FOR_EDIT, claim );
		return JspPageName.CLAIM_EDIT_PAGE;
	}
	
	

}
