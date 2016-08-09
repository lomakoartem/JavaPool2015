package repository;

import Entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lomak on 30.03.2016.
 */
public class InMemOrderRepository implements OrderRepository {
    private List<Order> orders = new ArrayList<>();
    @Override
    public Long saveOrder(Order order) {
    orders.add(order);
        return order.getId();
    }
}
