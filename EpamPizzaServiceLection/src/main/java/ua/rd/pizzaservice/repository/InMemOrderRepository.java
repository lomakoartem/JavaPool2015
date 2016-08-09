package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Order;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andrii
 */
@Repository("orderRepository")
public class InMemOrderRepository implements OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public Long saveOrder(Order newOrder) {
        orders.add(newOrder);
        return newOrder.getId();
    }
}
