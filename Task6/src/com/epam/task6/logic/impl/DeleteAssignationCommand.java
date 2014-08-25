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
import com.epam.task6.entity.ClaimStatusStringValue;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;
import com.epam.task6.tableentity.AssignationTableEntity;
import com.epam.task6.tableentity.ClaimTableEntity;
/**
*  ласс, реализующий {@link com.epam.task6.logic.ICommand} реализующий команду удалени€ 
* назначени€ оператором системы.
* @author dmitry
*
*/
public class DeleteAssignationCommand implements ICommand {

	/**
	 * @return адрес страницы опреатора.
	 */
	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException, CommandLogicException {

		HttpSession session = request.getSession();
		int assignationId = Integer.valueOf( request.getParameter( DBField.ASSIGNATION_ID ) );
		int claimId = Integer.valueOf( request.getParameter( DBField.CLAIM_ID ) );
		
		
		try {
			IDataDao dataDao = DaoFactory.getInstance().getDataDao();
			dataDao.deleteAssignation( assignationId, claimId );
		} catch ( DaoException e ) {
			new CommandException( "Exception in \"DeleteAssignationCommand\"",e );
		}
		
		List<AssignationTableEntity> assignationsList = (List<AssignationTableEntity>) session.getAttribute( SessionParameterName.ASSIGNATIONS );
		assignationsList = deleteAssignationFromList(assignationsList , assignationId );
		session.setAttribute( SessionParameterName.ASSIGNATIONS, assignationsList );
		
		List<ClaimTableEntity> claims = (List<ClaimTableEntity>) session.getAttribute( SessionParameterName.CLAIMS );
		claims = updateClaimFromList( claims, claimId  );
		session.setAttribute( SessionParameterName.CLAIMS, claims );
		
		
		return JspPageName.OPERATOR_PAGE;
	}
	
	/**
	 * ћетод, удал€ющий назначение из списка активных назначений.
	 * @param assignations список активных назначений
	 * @param assignationId идентификатор удал€емого назначени€
	 * @return обновленный список
	 */
	private List<AssignationTableEntity> deleteAssignationFromList(List<AssignationTableEntity> assignations, int assignationId){
		
		List<AssignationTableEntity> temp = new ArrayList<>();
		temp.addAll( assignations );
		
		for(AssignationTableEntity checkedAssignation : assignations){
			if(checkedAssignation.getAssignationId() == assignationId){
				temp.remove( checkedAssignation );
			}
		}
		return temp;
	}
	/**
	 * ћетод, измен€ющий статус за€вки назначение на которую удал€етс€.
	 * @param claimId идентификатор за€вки
	 * @param claims список за€вок
	 * @return обновленный список
	 */
	private List<ClaimTableEntity> updateClaimFromList( List<ClaimTableEntity> claims, int claimId ){
		
		List<ClaimTableEntity> temp = new ArrayList<>();
		temp.addAll( claims );
		
		for(ClaimTableEntity claim : claims){
			if(claim.getClaimId() == claimId ){
				int index = claims.indexOf( claim );
				temp.get( index ).setClaimStatus( ClaimStatusStringValue.EXCEPTED );
			}
		}
		return temp;
	}

}
