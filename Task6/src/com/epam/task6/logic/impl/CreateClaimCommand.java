package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.entity.Address;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;

public class CreateClaimCommand implements ICommand {

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException, CommandLogicException {
		Address address = new Address();
		address.setAddressId( Integer.valueOf( request
				.getParameter( RequestParameterName.ADDRESS_ID ) ) );
		address.setStreet( request.getParameter( RequestParameterName.STREET ) );
		address.setHouseNumber( Integer.valueOf( request
				.getParameter( RequestParameterName.HOUSE ) ) );
		address.setBlockNumber( Integer.valueOf( request
				.getParameter( RequestParameterName.BLOCK ) ) );
		address.setFlatNumber( Integer.valueOf( request
				.getParameter( RequestParameterName.FLAT ) ) );
		address.setUserId( Integer.valueOf( request
				.getParameter( RequestParameterName.USER_ID ) ) );
		request.getSession().setAttribute( "claimAddress", address );

		return JspPageName.CLAIM_PAGE;
	}

}
