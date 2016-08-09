package ua.epam.web.bean;

import ua.epam.dao.DaoFactory;
import ua.epam.dao.RoomClassDao;
import ua.epam.entities.RoomClass;

import java.util.List;

/**
 * Created by lomak on 19.01.2016.
 */
public class RoomClassQuery  {

    /**
     *
     * @return list of room classes
     */
    public List<RoomClass> getRoomClassList(){
        DaoFactory daoFactory = DaoFactory.getInstance();
        RoomClassDao roomClassDao = daoFactory.createRoomClassDao();
        return  roomClassDao.findAll();
    }
}