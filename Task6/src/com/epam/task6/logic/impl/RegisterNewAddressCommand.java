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
import com.epam.task6.dao.IDao;
import com.epam.task6.dao.IDataDao;
import com.epam.task6.entity.Address;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;

public class RegisterNewAddressCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException,
			CommandLogicException {
		
		HttpSession session = request.getSession();
		Address address = new Address();
		address.setUserId( Integer.valueOf( request.getParameter( RequestParameterName.USER_ID ) ) );
		address.setStreet( request.getParameter( RequestParameterName.STREET) );
		address.setHouseNumber( Integer.valueOf( request.getParameter( RequestParameterName.HOUSE ) ) );
		address.setBlockNumber( Integer.valueOf( request.getParameter( RequestParameterName.BLOCK ) ) );
		address.setFlatNumber( Integer.valueOf( request.getParameter( RequestParameterName.FLAT ) ) );
		address.setPhone( request.getParameter( RequestParameterName.PHONE ) );
		
		IDataDao dataDao = DaoFactory.getInstance().getDataDao();
		try {
			dataDao.addAddress( address );
		} catch ( DaoException e ) {
			throw new CommandException( "Exception in \"RegisterNewAddressCommand\"", e );
		}
		
		
		List<Address> addresses = (List<Address>) session.getAttribute( SessionParameterName.ADDRESSES );
		addresses.add( address );
		session.setAttribute( SessionParameterName.ADDRESSES, addresses );
		return JspPageName.USER_PAGE;
	}

}
