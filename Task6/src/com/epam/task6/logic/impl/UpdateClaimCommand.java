package com.epam.task6.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.task6.controller.RequestParameterName;
import com.epam.task6.controller.SessionParameterName;
import com.epam.task6.dao.DaoException;
import com.epam.task6.dao.DaoFactory;
import com.epam.task6.dao.IDataDao;
import com.epam.task6.entity.Claim;
import com.epam.task6.logic.CommandException;
import com.epam.task6.logic.CommandLogicException;
import com.epam.task6.logic.ICommand;
import com.epam.task6.tableentity.ClaimTableEntity;
/**
* Класс, реализующий {@link com.epam.task6.logic.ICommand} реализующий команду обновления
* данных заявки.
* @author dmitry
*
*/
public class UpdateClaimCommand implements ICommand{

	/**
	 * @return адрес страницы пользователя системы.
	 */
	@Override
	public String execute( HttpServletRequest request,
			HttpServletResponse response ) throws CommandException,
			CommandLogicException {
		
		HttpSession session = request.getSession();
		int claimId =  Integer.valueOf( request.getParameter( RequestParameterName.CLAIM_ID ));
		String problemDescription = request.getParameter( RequestParameterName.PROBLEM_DESCRIPTION );
			
		try {
			IDataDao dataDao = DaoFactory.getInstance().getDataDao();
			dataDao.updateClaim( claimId, problemDescription );
		} catch ( DaoException e ) {
			throw new CommandException( "Exception in \"UpdateClaimCommand\"", e );
		}
		
		List<ClaimTableEntity> claims = (List<ClaimTableEntity>) session.getAttribute( SessionParameterName.CLAIMS );
		claims = updateClaimList( claimId, problemDescription, claims );
		session.setAttribute( SessionParameterName.CLAIMS, claims );
		session.removeAttribute( SessionParameterName.CLAIM_FOR_EDIT );
		
		return (String) session.getAttribute( SessionParameterName.USER_PAGE );
	}

	private List<ClaimTableEntity> updateClaimList(int claimId, String problemDescription, List<ClaimTableEntity> claims){
		
		List<ClaimTableEntity> temp = new ArrayList<ClaimTableEntity>();
		temp.addAll( claims );
		for(ClaimTableEntity claim : claims){
			if( claim.getClaimId() == claimId ){
				claim.setProblemDescription( problemDescription );
			}
		}
		return claims;
	}
}
