package ua.epam.web.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.epam.resource.ConfigurationManager;
import ua.epam.dao.DaoFactory;
import ua.epam.dao.RoomClassDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lomak on 19.01.2016.
 */
public class DeleteRoomClassCommand implements Command {

    public static final String PARAM_NAME_ID = "id";


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        try{

            String sId = request.getParameter(PARAM_NAME_ID);
            int id = Integer.parseInt(sId);
            DaoFactory daoFactory = DaoFactory.getInstance();
            RoomClassDao roomClassDao = daoFactory.createRoomClassDao();

            roomClassDao.delete(id);

        } catch (NumberFormatException e){

            Logger logger = LogManager.getLogger(DeleteRoomClassCommand.class.getName());
            logger.error("Error creation room " + e);

        }


        return ConfigurationManager.getProperty("path.page.roomclass");
    }

}