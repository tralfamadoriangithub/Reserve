package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.entity.Address;
import com.epam.task6.logic.ICommand;

public class CreateClaimCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) {
		Address address = new Address();
		address.setAddressId( Integer.valueOf( request.getParameter( "address_id" ) ) );
		address.setStreet( request.getParameter( "street" ) );
		address.setHouseNumber(Integer.valueOf( request.getParameter( "house" ) ));
		address.setBlockNumber( Integer.valueOf( request.getParameter( "block" ) ) );
		address.setFlatNumber( Integer.valueOf( request.getParameter( "flat" ) ) );
		address.setUserId( Integer.valueOf( request.getParameter( "user_id" ) ) );
		request.getSession().setAttribute( "address", address );
		
		return JspPageName.CLAIM_PAGE;
	}

}
