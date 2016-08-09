package ua.epam.web.command;

import ua.epam.resource.ConfigurationManager;
import ua.epam.dao.DaoFactory;
import ua.epam.dao.RoomDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lomak on 19.01.2016.
 */
public class DeleteRoomCommand implements Command {

    public static final String PARAM_NAME_ID = "id";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        String sId = request.getParameter(PARAM_NAME_ID);
        int id = Integer.parseInt(sId);
        DaoFactory daoFactory = DaoFactory.getInstance();
        RoomDao roomDao = daoFactory.createRoomDao();

        roomDao.delete(id);

        return ConfigurationManager.getProperty("path.page.rooms");
    }

}