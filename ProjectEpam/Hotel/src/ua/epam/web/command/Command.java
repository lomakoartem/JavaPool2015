package ua.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lomak on 19.01.2016.
 */

public interface Command {

    String execute(HttpServletRequest request , HttpServletResponse response);

}