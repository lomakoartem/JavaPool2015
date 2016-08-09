package ua.epam.web.command;

import ua.epam.resource.ConfigurationManager;
import ua.epam.dao.DaoFactory;
import ua.epam.dao.RoomClassDao;
import ua.epam.entities.RoomClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lomak on 19.01.2016.
 */
public class CreateRoomClassCommand implements Command {

    public static final String PARAM_NAME_NAME = "name";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        String name = request.getParameter(PARAM_NAME_NAME);
        DaoFactory daoFactory = DaoFactory.getInstance();
        RoomClassDao roomClassDao = daoFactory.createRoomClassDao();

        RoomClass roomClass = new RoomClass();
        roomClass.setName(name);

        roomClassDao.create(roomClass);

        return ConfigurationManager.getProperty("path.page.roomclass");
    }
}