package ua.epam.web.bean;
import ua.epam.dao.DaoFactory;
import ua.epam.dao.RoomDao;
import ua.epam.entities.Order;
import ua.epam.entities.Room;
import java.util.List;

/**
 * Created by lomak on 19.01.2016.
 */
public class PickUpRoomQuery {

    private Order order;

    /**
     *
     * @return list of sutable rooms
     */
    public List<Room> getRoomsList(){
        DaoFactory daoFactory = DaoFactory.getInstance();
        RoomDao roomDao = daoFactory.createRoomDao();
        return  roomDao.findFree(order);
    }


    /**
     *
     * @param client's order
     */
    public void setOrder(Order order) {
        this.order = order;
    }


}