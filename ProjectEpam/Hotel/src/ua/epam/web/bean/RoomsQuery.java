package ua.epam.web.bean;

import ua.epam.dao.DaoFactory;
import ua.epam.dao.RoomDao;
import ua.epam.entities.Room;

import java.util.List;

/**
 * Created by lomak on 19.01.2016.
 */
public class RoomsQuery {

    /**
     *
     * @return list of all rooms
     */
    public List<Room> getRoomsList(){
        DaoFactory daoFactory = DaoFactory.getInstance();
        RoomDao roomDao = daoFactory.createRoomDao();
        return  roomDao.findAll();
    }

}