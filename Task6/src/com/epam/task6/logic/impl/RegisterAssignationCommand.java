package com.epam.task6.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.controller.SessionParameterName;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.IDataDao;
import com.epam.task6.entity.Assignation;
import com.epam.task6.entity.Claim;
import com.epam.task6.entity.ClaimStatusStringValue;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;
import com.epam.task6.tableentity.AssignationTableEntity;
import com.epam.task6.tableentity.ClaimTableEntity;

public class RegisterAssignationCommand implements ICommand {

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException, CommandLogicException {
		HttpSession session = request.getSession();
		
		ClaimTableEntity claim = (ClaimTableEntity) session
				.getAttribute( SessionParameterName.CLAIM_FOR_ASSIGNATION );

		String[] strWorkerId = request
				.getParameterValues( RequestParameterName.SELECTED_WORKER );
		
		int[] workersId = getWorkersId( strWorkerId );

		Timestamp beginWork = createTimestamp(
				request.getParameter( RequestParameterName.BEGIN_WORK_DATE ),
				request.getParameter( RequestParameterName.BEGIN_WORK_TIME ) );
		Timestamp endWork = createTimestamp(
				request.getParameter( RequestParameterName.END_WORK_DATE ),
				request.getParameter( RequestParameterName.END_WORK_TIME ) );

		Assignation assignation = new Assignation();
		assignation.setClaimId( claim.getClaimId() );
		assignation.setBeginWork( beginWork );
		assignation.setEndWork( endWork );
		
		try {
			IDataDao dataDao = DaoFactory.getInstance().getDataDao();
			dataDao.registerNewAssignation( assignation, workersId );
		} catch ( DaoException e ) {
			throw new CommandException(
					"Exception in \"RegisterAssignationCommand\"", e );
		}
		
		List<ClaimTableEntity> claimsList = (List<ClaimTableEntity>) session.getAttribute( SessionParameterName.CLAIMS );
		claimsList = updateClaimFromList( claim.getClaimId(), claimsList );
		session.setAttribute( SessionParameterName.CLAIMS, claimsList );
		
		claim.setClaimStatus( ClaimStatusStringValue.PROCESSED );
		
		AssignationTableEntity assignationTableEntity = new AssignationTableEntity();
		assignationTableEntity.setAssignationId( assignation.getAssignationId() );
		assignationTableEntity.setBeginWork( beginWork );
		assignationTableEntity.setEndWork( endWork );
		assignationTableEntity.setClaim( claim );
		
		List<AssignationTableEntity> assignations = (List<AssignationTableEntity>) session.getAttribute( SessionParameterName.ASSIGNATIONS );
		assignations.add( assignationTableEntity );
		session.setAttribute( SessionParameterName.ASSIGNATIONS, assignations );

		session.removeAttribute( SessionParameterName.CLAIM_FOR_ASSIGNATION );
		
		return JspPageName.OPERATOR_PAGE;
	}

	private Timestamp createTimestamp( String date, String time ) {
		StringBuilder dateTime = new StringBuilder();
		dateTime.append( date ).append( " " ).append( time ).append( ":00" );
		Timestamp timestamp = Timestamp.valueOf( dateTime.toString() );
		return timestamp;
	}

	private int[] getWorkersId( String[] strWorkersId ) {
		
		int[] intWorkersId = new int[strWorkersId.length];

		for ( int i = 0; i < strWorkersId.length; i++ ) {
			intWorkersId[i] = Integer.parseInt( strWorkersId[i] );
		}
		return intWorkersId;
	}
	
	private List<ClaimTableEntity> updateClaimFromList( int claimId, List<ClaimTableEntity> claims ){
		System.out.println("Claim update : " + claims);
		System.out.println("Claim ID : " + claimId);
		List<ClaimTableEntity> temp = new ArrayList<>();
		temp.addAll( claims );
		
		for(ClaimTableEntity claim : claims){
			if(claim.getClaimId() == claimId ){
				int index = claims.indexOf( claim );
				System.out.println("Claim index : " + index);
				temp.get( index ).setClaimStatus( ClaimStatusStringValue.PROCESSED );
			}
		}
		return temp;
	}
}
