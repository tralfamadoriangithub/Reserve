package com.epam.task6.logic.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javax.servlet.http.HttpSession;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.IDataDao;
import com.epam.task6.entity.Address;
import com.epam.task6.entity.Claim;
import com.epam.task6.entity.User;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.ICommand;
import com.epam.task6.tableentity.ClaimTableEntity;

public class SendClaimCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException {
		
		HttpSession session = request.getSession();
		ClaimTableEntity claimTable = new ClaimTableEntity();
		Claim newClaim = new Claim();
		
		Address address = (Address) session.getAttribute( "claimAddress" );
		User user = (User) session.getAttribute( "user" );
		
		claimTable.setAddress( address );
		claimTable.setProblemDescription( request.getParameter( RequestParameterName.PROBLEM_DESCRIPTION ) );
		request.getSession().removeAttribute( "claimAddress" );
		
		newClaim.setAddressId( address.getAddressId() );
		newClaim.setUserId( user.getUserId() );
		newClaim.setProblemDescription( claimTable.getProblemDescription() );
		
		IDataDao dataManager = DaoFactory.getInstance().getDataDao();
		
		try {
			dataManager.addClaim( newClaim );
			claimTable.setClaimStatus( "Sended" );
		} catch ( DaoException e ) {
			System.out.println("Exception");
			throw new CommandException( "Exception in \"SendClaimCommand\"", e );	
		}
		
		List<ClaimTableEntity> claims = (List<ClaimTableEntity>) session.getAttribute( "claims" );
		claims.add( claimTable );
		System.out.println(claimTable.getClaimId());
		session.setAttribute( "claims", claims );
		return JspPageName.USER_PAGE;
	}
}
