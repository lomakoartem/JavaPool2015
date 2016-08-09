package ua.epam.web.bean;

import ua.epam.dao.DaoFactory;
import ua.epam.dao.UserDao;
import ua.epam.entities.User;

import java.util.List;

/**
 * Created by lomak on 19.01.2016.
 */
public class UsersQuery {

    /**
     *
     * @return list of all users
     */
    public List<User> getUsersList(){
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        return  userDao.findAll();
    }
}