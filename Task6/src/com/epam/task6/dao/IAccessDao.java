package com.epam.task6.dao;
import com.epam.task6.entity.User;
/**
 * ���������, ����������� ������ ��������� ������������.
 * 
 * ������� ������. 
 * 
 * @author dmitry
 *
 */
public interface IAccessDao {
	/**
	 * �����, ��� ����� ������������ � �������.
	 * 
	 * @param login ����� ������������.
	 * @param password ������ ������������.
	 * @return ��������� ������ {@link com.epam.task5.entity.User}
	 * @throws DaoException ��� ������������� ������ ���������.
	 * @throws DataNotFoundException ��� ���������� ������ ������������.
	 */
	public User signIn( String login, String password ) throws DaoException, DataNotFoundException;
}
