package com.epam.task6.dao;
import com.epam.task6.entity.User;
/**
 * »нтерфейс, объ€вл€ющий методы логинации пользовател€.
 * 
 *  ажетс€ лишним. 
 * 
 * @author dmitry
 *
 */
public interface IAccessDao {
	/**
	 * ћетод, дл€ входа пользовател€ в систему.
	 * 
	 * @param login логин пользовател€.
	 * @param password пароль пользовател€.
	 * @return экземпл€р класса {@link com.epam.task5.entity.User}
	 * @throws DaoException при возникновении ошибки источника.
	 * @throws DataNotFoundException при отсутствии записи пользовател€.
	 */
	public User signIn( String login, String password ) throws DaoException, DataNotFoundException;
}
