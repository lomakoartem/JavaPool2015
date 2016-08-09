package ua.epam.web.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.epam.resource.ConfigurationManager;
import ua.epam.dao.DaoFactory;
import ua.epam.dao.RoomDao;
import ua.epam.entities.Room;
import ua.epam.entities.RoomClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lomak on 19.01.2016.
 */
public class CreateRoomCommand implements Command {

    public static final String PARAM_NAME_NUMBER = "number";
    public static final String PARAM_NAME_BEDS = "beds";
    public static final String PARAM_NAME_ROOM_CLASS_ID = "roomclass";

    @Override

    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        String number = request.getParameter(PARAM_NAME_NUMBER);
        String beds = request.getParameter(PARAM_NAME_BEDS);
        String roomClassId = request.getParameter(PARAM_NAME_ROOM_CLASS_ID);

        DaoFactory daoFactory = DaoFactory.getInstance();
        RoomDao roomDao = daoFactory.createRoomDao();

        try{

            RoomClass roomClass = new RoomClass();
            roomClass.setId(Integer.parseInt(roomClassId));

            Room room = new Room();
            room.setBeds(Integer.parseInt(beds));
            room.setNumber(Integer.parseInt(number));
            room.setRoomClass(roomClass);

            roomDao.create(room);

        } catch (NumberFormatException e) {

            Logger logger = LogManager.getLogger(CreateRoomCommand.class.getName());
            logger.error("Error creation room " + e);

        }

        return ConfigurationManager.getProperty("path.page.rooms");
    }

}