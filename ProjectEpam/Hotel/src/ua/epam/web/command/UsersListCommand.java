package ua.epam.web.command;

import ua.epam.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lomak on 19.01.2016.
 */
public class UsersListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        return ConfigurationManager.getProperty("path.page.users");
    }

}