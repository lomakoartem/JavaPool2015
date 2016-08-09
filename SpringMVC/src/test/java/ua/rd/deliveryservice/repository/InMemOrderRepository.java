package ua.rd.deliveryservice.repository;

import java.util.ArrayList;
import java.util.List;
import ua.rd.deliveryservice.domain.Order;
import ua.rd.deliveryservice.repository.OrderRepository;

/**
 *
 * @author andrii
 */
public class InMemOrderRepository implements OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Order save(Order order) {
        orders.add(order);
        return order;
    }

}
