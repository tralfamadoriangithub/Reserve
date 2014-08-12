package com.epam.task6.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.controller.SessionParameterName;
import com.epam.task6.entity.Address;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;
import com.epam.task6.tableentity.ClaimTableEntity;

public class CreateAssignationCommand implements ICommand {

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException, CommandLogicException {
		ClaimTableEntity claim = new ClaimTableEntity();
		Address address = new Address();

		address.setAddressId( Integer.parseInt( request.getParameter( RequestParameterName.ADDRESS_ID ) ) );
		address.setUserId( Integer.parseInt( request.getParameter( RequestParameterName.USER_ID ) ) );
		address.setStreet( request.getParameter( RequestParameterName.STREET ) );
		address.setHouseNumber( Integer.parseInt( request.getParameter( RequestParameterName.HOUSE ) ) );
		address.setBlockNumber( Integer.parseInt( request.getParameter( RequestParameterName.BLOCK ) ) );
		address.setFlatNumber( Integer.parseInt( request.getParameter( RequestParameterName.FLAT ) ) );
		address.setPhone( request.getParameter( RequestParameterName.PHONE )  );
		
		claim.setAddress( address );
		claim.setClaimId( Integer.parseInt( request.getParameter( RequestParameterName.CLAIM_ID ) ) );
		claim.setProblemDescription( request.getParameter( RequestParameterName.PROBLEM_DESCRIPTION ) );
		claim.setClaimStatus( request.getParameter( RequestParameterName.CLAIM_STATUS ) );
		request.getSession().setAttribute( SessionParameterName.CLAIM_FOR_ASSIGNATION, claim );
		return JspPageName.ASSIGNATION_PAGE;
	}

}
