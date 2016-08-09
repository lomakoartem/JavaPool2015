package ua.epam.web.command;
import ua.epam.resource.ConfigurationManager;
import ua.epam.dao.DaoFactory;
import ua.epam.dao.UserDao;
import ua.epam.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterCommand implements Command{

    private final static String PARAM_NAME_LOGIN = "login";
    private final static String PARAM_NAME_NAME = "name";
    private final static String PARAM_NAME_PASSWORD = "password";
    private final static String PARAM_NAME_EMAIL = "email";
    private final static String PARAM_NAME_CONFIRMPASSWORD = "confirmPassword";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        String login = request.getParameter(PARAM_NAME_LOGIN);
        String name = request.getParameter(PARAM_NAME_NAME);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String confirmPassword = request.getParameter(PARAM_NAME_CONFIRMPASSWORD);

        String errorMessage = "";

        DaoFactory daoFactory = DaoFactory.getInstance();

        UserDao userDao = daoFactory.createUserDao();


        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPasswordHash(password);
        user.setEmail(email);
        user.setAdministrator(false);

        if(!userDao.isLoginFree(user)){
            errorMessage += "Login çàíÿò \r\n" ;
        }

        if(password.length() < 5){
            errorMessage += "password must consists at least of 5 symbols \r\n";
        }


        if(!Objects.equals(password,confirmPassword)){
            errorMessage += " password do not matches \r\n";
        }


        if("".equals(errorMessage)){
            userDao.create(user);
            return ConfigurationManager.getProperty("path.page.index");
        }

        request.setAttribute("errorMessage", errorMessage);
        return  ConfigurationManager.getProperty("path.page.registration");



    }

}