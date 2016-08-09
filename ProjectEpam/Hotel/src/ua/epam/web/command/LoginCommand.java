package ua.epam.web.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.epam.resource.ConfigurationManager;
import ua.epam.dao.DaoFactory;
import ua.epam.dao.UserDao;
import ua.epam.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lomak on 19.01.2016.
 */
public class LoginCommand implements Command {

    public static final String PARAM_NAME_LOGIN = "login";
    public static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        Logger logger = LogManager.getLogger(LoginCommand.class.getName());

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();

        User user = new User();
        user.setLogin(login);
        user.setPasswordHash(password);

        if(!userDao.login(user)){

            logger.warn("User " + login + " access denied ");

            request.setAttribute("errorMessage", "access denied");
            return ConfigurationManager.getProperty("path.page.index");
        }


        logger.info("User " + user + " login ");

        HttpSession session = request.getSession();

        session.setAttribute("user", user);


        if(user.getAdministrator()){

            return ConfigurationManager.getProperty("path.page.admin");

        } else {


            return ConfigurationManager.getProperty("path.page.user");
        }
    }


}