package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Order;
import java.util.ArrayList;
import java.util.List;

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
