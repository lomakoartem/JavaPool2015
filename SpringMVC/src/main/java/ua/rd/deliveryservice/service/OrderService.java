package ua.rd.deliveryservice.service;

import ua.rd.deliveryservice.domain.Customer;
import ua.rd.deliveryservice.domain.Order;

/**
 *
 * @author andrii
 */
public interface OrderService {

    Order placeNewOrder(Customer customer, int... pizzaID);

}
