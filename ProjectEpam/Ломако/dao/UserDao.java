package ua.epam.dao;

import ua.epam.entities.User;

public abstract class UserDao {

	public abstract User findUser(String login, String password) throws DaoException;

}
