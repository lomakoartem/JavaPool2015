package ua.epam.web.command;

import ua.epam.resource.ConfigurationManager;
import ua.epam.dao.DaoFactory;
import ua.epam.dao.OrderDao;
import ua.epam.entities.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lomak on 19.01.2016.
 */
public class SetRoomCommand implements Command {

    public static final String PARAM_NAME_PROCESSING_ORDER = "processingOrder";
    public static final String PARAM_NAME_ROOM_ID = "roomId";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute(PARAM_NAME_PROCESSING_ORDER);
        String roomIdStr = request.getParameter(PARAM_NAME_ROOM_ID);
        int roomId = Integer.valueOf(roomIdStr);
        session.removeAttribute(PARAM_NAME_PROCESSING_ORDER);

        DaoFactory daoFactory = DaoFactory.getInstance();
        OrderDao orderDao = daoFactory.createOrderDao();
        orderDao.setRoom(order, roomId);

        return ConfigurationManager.getProperty("path.page.unprocessedOrders");
    }

}