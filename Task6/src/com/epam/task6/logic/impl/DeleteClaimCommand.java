package com.epam.task6.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.controller.SessionParameterName;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.IDataDao;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;
import com.epam.task6.tableentity.ClaimTableEntity;

public class DeleteClaimCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException, CommandLogicException {
		HttpSession session = request.getSession();
		int claimId = Integer.valueOf( request.getParameter( RequestParameterName.CLAIM_ID ) );
		
		try {
			IDataDao dataDao = DaoFactory.getInstance().getDataDao();
			dataDao.deleteClaim( claimId );
		} catch ( DaoException e ) {
			throw new CommandException( "Exception in \"DeleteClaimCommand\"", e );
		}
		
		List<ClaimTableEntity> claims = (List<ClaimTableEntity>) session.getAttribute( SessionParameterName.CLAIMS );
		claims = deleteClaimFromList( claimId, claims );
		session.setAttribute( SessionParameterName.CLAIMS, claims );
		
		return (String) session.getAttribute( SessionParameterName.USER_PAGE );
	}
	
	private List<ClaimTableEntity> deleteClaimFromList(int claimId, List<ClaimTableEntity> claims ){
		List<ClaimTableEntity> temp = new ArrayList<>();
		temp.addAll( claims );
		
		for(ClaimTableEntity claim : claims){
			if( claim.getClaimId() == claimId ){
				temp.remove( claim );
			}
		}
		return temp;
	}

}
