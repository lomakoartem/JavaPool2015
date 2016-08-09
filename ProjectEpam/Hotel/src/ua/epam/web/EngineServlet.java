package ua.epam.web;

import ua.epam.web.command.Command;
import ua.epam.web.command.factory.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lomak on 19.01.2016.
 */
@WebServlet(name = "EngineServlet", urlPatterns = {"/EngineServlet"})
public class EngineServlet extends HttpServlet {

    private static final long serialVersionUID = -4756323297516657195L;


    /**
     *
     * Process requests received by POST and GET method
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        CommandFactory client = new CommandFactory();
        Command command = client.defineCommand(request);

        String page = null;

        if (command != null) {
            page = command.execute(request, response);
        }

        if(page != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        processRequest(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        processRequest(req, resp);
    }



}