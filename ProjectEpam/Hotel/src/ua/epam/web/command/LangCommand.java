package ua.epam.web.command;

import ua.epam.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lomak on 21.01.2016.
 */
public class LangCommand implements Command {
    private static final String PARAM_NAME_LANGUAGE = "language";
    /**
     * Localization: supports russian and english language, ukrainian is not
     * currently implemented.
     */
    @Override
 public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.login");
        String language = request.getParameter(PARAM_NAME_LANGUAGE);
        switch (language) {
            case "en":
                request.getSession().setAttribute("lang", "en");
                break;
            case "ru":
                request.getSession().setAttribute("lang", "ru");
                break;
        }
        return page;
    }

}
