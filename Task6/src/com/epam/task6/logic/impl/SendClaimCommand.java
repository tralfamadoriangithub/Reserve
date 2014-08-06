package com.epam.task6.logic.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javax.servlet.http.HttpSession;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.IDataManager;
import com.epam.task6.entity.Address;
import com.epam.task6.entity.Claim;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.ICommand;

public class SendClaimCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException {
		HttpSession session = request.getSession();
		Claim claim = new Claim();
		Address address = (Address) session.getAttribute( "claimAddress" );
		claim.setAddressId( address.getAddressId() );
		claim.setUserId( address.getUserId() );
		claim.setProblemDescription( request.getParameter( RequestParameterName.PROBLEM_DESCRIPTION ) );
		request.getSession().removeAttribute( "claimAddress" );
		IDataManager dataManager = DaoFactory.getInstance().getDataManager();
		
		try {
			dataManager.addClaim( claim );
		} catch ( DaoException e ) {
			throw new CommandException( "Exception in \"SendClaimCommand\"", e );
		}
		
		List<Claim> claims = (List<Claim>) session.getAttribute( "claims" );
		claims.add( claim );
		System.out.println(claim.getClaimId());
		session.setAttribute( "claims", claims );
		return JspPageName.USER_PAGE;
	}
}
