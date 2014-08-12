package com.epam.task6.logic.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.JspPageName;
import com.epam.task6.controller.SessionParameterName;
import com.epam.task6.dao.DBField;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.IDataDao;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.ICommand;
import com.epam.task6.tableentity.AssignationTableEntity;

public class DeleteAssignationCommand implements ICommand {

	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException {

		HttpSession session = request.getSession();
		int assignationId = Integer.valueOf( request.getParameter( DBField.ASSIGNATION_ID ) );
		
		IDataDao dataDao = DaoFactory.getInstance().getDataDao();
		try {
			dataDao.deleteAssignation( assignationId );
		} catch ( DaoException e ) {
			new CommandException( "Exception in \"DeleteAssignationCommand\"",e );
		}
		
		List<AssignationTableEntity> assignationsList = (List<AssignationTableEntity>) session.getAttribute( SessionParameterName.ASSIGNATIONS );
		assignationsList = deleteAssignation(assignationsList , assignationId );
		session.setAttribute( SessionParameterName.ASSIGNATIONS, assignationsList );
		return JspPageName.OPERATOR_PAGE;
	}
	
	private List<AssignationTableEntity> deleteAssignation(List<AssignationTableEntity> oldAssignationsList, int assignationId){
		
		List<AssignationTableEntity> temp = new ArrayList<>();
		temp.addAll( oldAssignationsList );
		for(AssignationTableEntity checkedAssignation : oldAssignationsList){
			if(checkedAssignation.getAssignationId() == assignationId){
				temp.remove( checkedAssignation );
			}
		}
		return temp;
	}

}
