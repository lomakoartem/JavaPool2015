package ua.epam.web.bean;

import ua.epam.dao.DaoFactory;
import ua.epam.dao.OrderDao;
import ua.epam.entities.Order;

import java.util.List;

/**
 * Created by lomak on 19.01.2016.
 */
public class UnprocessedOrdersQuery {

    List<Order> orders;
    int orderId;

    /**
     * Constructor queries all unprocessed orders
     */
    public UnprocessedOrdersQuery() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        OrderDao orderDao = daoFactory.createOrderDao();
        orders = orderDao.findUnprocessed();
    }

    /**
     *
     * @return all unprocessed orders
     */
    public List<Order> getUnprocessedOrders(){
        return orders;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    /**
     *
     * @return order with setted id
     */
    public Order getOrder(){
        for(Order order: orders){
            if(order.getId() == orderId){
                return order;
            }
        }
        return null;
    }



}