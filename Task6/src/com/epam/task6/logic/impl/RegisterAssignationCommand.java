package com.epam.task6.logic.impl;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

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
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.ICommand;
import com.epam.task6.tableentity.ClaimTableEntity;

public class RegisterAssignationCommand implements ICommand{

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException {
		HttpSession session = request.getSession();
		ClaimTableEntity claim = (ClaimTableEntity) session.getAttribute( SessionParameterName.CLAIM_FOR_ASSIGNATION );
		String [] workersId = request.getParameterValues( RequestParameterName.SELECTED_WORKER );
		String dateB = request.getParameter( RequestParameterName.BEGIN_WORK_DATE );
		String dateE = request.getParameter( RequestParameterName.END_WORK_DATE );
		
		System.out.println(dateE);
		Assignation assignation = new Assignation();
		assignation.setClaimId( claim.getClaimId() );
	
//		IDataDao dataDao = DaoFactory.getInstance().getDataDao();
//		try {
//			dataDao.addAssignation( assignation );
//		} catch ( DaoException e ) {
//			throw new CommandException( "Exception in \"RegisterAssignationCommand\"", e );
//		}
		return JspPageName.OPERATOR_PAGE;
	}

}
