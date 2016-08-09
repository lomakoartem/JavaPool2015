package ua.epam.web.bean;

import ua.epam.dao.DaoFactory;
import ua.epam.dao.OrderDao;
import ua.epam.entities.Order;
import ua.epam.entities.User;

import java.util.List;

/**
 * Created by lomak on 19.01.2016.
 */
public class UserOrdersQuery {

    private User user;

    /**
     *
     * @param user to filter orders
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return list of processed orders by user
     */
    public List<Order> getOrdersByUser(){
        DaoFactory daoFactory = DaoFactory.getInstance();
        OrderDao orderDao = daoFactory.createOrderDao();
        return  orderDao.findByUser(user);
    }

}