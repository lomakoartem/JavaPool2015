package ua.epam.dao;

import ua.epam.entities.Order;
import ua.epam.entities.User;

import java.util.List;

/**
 * Created by lomak on 18.01.2016.
 */
public interface OrderDao {


    /**
     * Creates new order in database used object of Order class
     * @param order
     *
     */
    void create(Order order);


    /**
     * Deletes from database object with passed id
     * @param id - id of object for deleting
     *
     */
    void delete(int id);


    /**
     * Finds orders which made custmer but administrator
     * haven't processed
     * @return list of unprocessed orders
     *
     */
    List<Order> findUnprocessed();


    /**
     * Finds orders which made defined user and processed administrator
     *
     * @param user filter of orders to be found
     * @return list of processed orders by user
     *
     */
    List<Order> findByUser(User user);


    /**
     * Sets room id to order in during processing orders
     *
     * @param order - processing order
     * @param roomId - id of room which attaches to this order
     *
     */
    void setRoom(Order order, int roomId);

}